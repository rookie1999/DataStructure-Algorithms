package cn.zhanguozhi.ADT;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements IMyList<AnyType> {

    // 默认初始大小
    private static final int DEFAULT_SIZE = 10;
    // 表中存放的对象容量
    private int size = 0;
    // 与迭代器进行同步(只要是数组中元素个数发生变化  就要修改此值)
    private int realModifiedCount = 0;
    // 底层存放对象的数组
    private AnyType[] arrayList;

    public MyArrayList() {
        arrayList = (AnyType[]) new Object[DEFAULT_SIZE];
    }
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity:" + capacity);
        }
        arrayList = (AnyType[]) new Object[capacity];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        doClear();
    }

    /**
     * 清空时恢复至默认大小
     * 因为对数组元素修改  需要同步realModifiedCount
     */
    private void doClear() {
        arrayList = (AnyType[]) new Object[DEFAULT_SIZE];
        realModifiedCount++;
        size = 0;
    }

    /**
     * 表中不允许存储null值
     * @param x
     * @return
     */
    @Override
    public boolean contains(AnyType x) {
        if (x == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        for (AnyType element : arrayList) {
            if (element == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在表的末尾处添加元素
     * @param x
     * @return
     */
    @Override
    public void addObject(AnyType x) {
        add(size, x);
    }

    /**
     * 在表中idx处添加元素
     * 如果表中元素大小和表的长度相等，需要动态扩容
     * 需要同步realModifiedCount
     * @param x
     * @param idx
     * @return
     */
    @Override
    public void add(int idx, AnyType x) {
        if (x == null || idx < 0 || idx > size) {
            throw new IllegalArgumentException("invalid parameter");
        }
        if (size == arrayList.length) {
            ensureCapacity();
        }
        for (int i = size - 1; i >= idx; i--) {
            arrayList[i + 1] = arrayList[i];
        }
        arrayList[idx] = x;
        realModifiedCount++;
        size++;
    }

    public void addAll(Iterable<? extends AnyType> items) {
        if (null == items) {
            throw new IllegalArgumentException();
        }
        java.util.Iterator<AnyType> iter = (java.util.Iterator<AnyType>) items.iterator();
        while (iter.hasNext()) {
            addObject(iter.next());
        }
    }

    public void removeAll(Iterable<? extends AnyType> items) {
        if (null == items) {
            throw new IllegalArgumentException();
        }
        java.util.Iterator<AnyType> iter = (java.util.Iterator<AnyType>) items.iterator();
        while (iter.hasNext()) {
            AnyType val = iter.next();
            if (contains(val)) {
                removeObject(val);
            }
        }
    }
    @Override
    public boolean removeObject(AnyType x) {
        if (x == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        int idx = -1;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == x) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return false;
        } else {
           remove(idx);
            return true;
        }

    }

    @Override
    public void remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("invalid parameter");
        }
        for (int i = idx; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        realModifiedCount++;
        size--;
    }

    @Override
    public AnyType get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("idx wrong");
        }
        return arrayList[idx];
    }

    /**
     * 设置idx的元素的值
     * 由于set方法不对arrayList的元素个数进行修改所以不需要进行同步
     * @param idx
     * @param newVal
     * @return 原来的idx处的元素的值
     */
    @Override
    public AnyType set(int idx, AnyType newVal) {
        if (newVal == null || idx < 0 || idx > size) {
            throw new IllegalArgumentException("invalid parameter");
        }
        AnyType oldVal = arrayList[idx];
        arrayList[idx] = newVal;
        return oldVal;
    }

    private void ensureCapacity() {
        AnyType[] newArrayList = (AnyType[]) new Object[arrayList.length * 2 + 1];
        for (int i = 0; i < size; i++) {
            newArrayList[i] = arrayList[i];
        }
        arrayList = newArrayList;
    }

    /**
     * 在增删完成之后，将表的容量和元素个数匹配
     */
    public void trimToSize() {
        if (size < arrayList.length) {
            AnyType[] newArrayList = (AnyType[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newArrayList[i] = arrayList[i];
            }
            arrayList = newArrayList;
        }
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(arrayList[i] + " ");
        }
        System.out.println();
    }
    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<AnyType> {
        // 与外部表进行同步
        private int expectedModifiedCount = realModifiedCount;
        // next指针
        private int current = 0;
        // 是否可以删除  调用next()之后为true，调用remove()之后为false
        private boolean okToRemove = false;
        // 是否可以迭代 调用hasNext()方法之后为true，调用next()方法之后为false
        private boolean okToNext = false;

        @Override
        public boolean hasNext() {
            okToNext = true;
            return current != size;
        }

        @Override
        public AnyType next() {
            if (!okToNext) {
                throw new IllegalStateException("cannot call next, should call hasNext first");
            }
            okToNext = false;
            okToRemove = true;
            return arrayList[current++];
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException("cannot call remove, should call next first");
            }
            if (realModifiedCount != expectedModifiedCount) {
                throw new ConcurrentModificationException();
            }
            okToRemove = false;
            MyArrayList.this.removeObject(arrayList[current - 1]);
            expectedModifiedCount++;
        }
    }

    public java.util.ListIterator<AnyType> listIterator() {
        return new ListIterator();
    }

    public java.util.ListIterator<AnyType> listIterator(int idx) {
        return new ListIterator(idx);
    }

    private class ListIterator implements java.util.ListIterator<AnyType> {

        private int index;
        private int expectedModCount = realModifiedCount;
        private AnyType lastReturned;
        private int lastIndex = -1;

        public ListIterator() {
        }

        public ListIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index != size();
        }

        @Override
        public AnyType next() {
            if (expectedModCount != realModifiedCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext())  {
                throw new NoSuchElementException();
            }
            lastIndex = index;
            lastReturned = arrayList[index++];
            return lastReturned;
        }

        @Override
        public boolean hasPrevious() {
            return index != 0;
        }

        @Override
        public AnyType previous() {
            if (expectedModCount != realModifiedCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious())  {
                throw new NoSuchElementException();
            }
            lastReturned = arrayList[--index];
            lastIndex = index;
            return lastReturned;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index--;
        }

        @Override
        public void remove() {
            if (expectedModCount != realModifiedCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            MyArrayList.this.removeObject(lastReturned);
            expectedModCount++;
        }

        @Override
        public void set(AnyType anyType) {
            if (expectedModCount != realModifiedCount) {
                throw new ConcurrentModificationException();
            }
            if (lastIndex < 0) {
                throw new IllegalStateException();
            }
            MyArrayList.this.set(lastIndex, anyType);

        }

        // 在要被next访问的index处增加元素
        @Override
        public void add(AnyType anyType) {
            if (expectedModCount != realModifiedCount) {
                throw new ConcurrentModificationException();
            }
            try {
                MyArrayList.this.add(index, anyType);
                index++;
                expectedModCount++;
                lastIndex = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
