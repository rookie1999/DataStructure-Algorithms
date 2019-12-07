package cn.zhanguozhi.ADT;


import java.util.ConcurrentModificationException;

import java.util.NoSuchElementException;

@SuppressWarnings("all")
public class MyLinkedList<AnyType> implements IMyList<AnyType> {

    /**
     * 嵌套类Node
     */
    private static class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;
        private Node<AnyType> prev;

        public Node(AnyType data, Node<AnyType> next, Node<AnyType> previous) {
            this.data = data;
            this.next = next;
            this.prev = previous;
        }
    }

    /**
     * 属性
     */
    // 存储元素个数(本质目的是为了让size()方法运行在常数时间)
    private int size;
    // 头节点
    private Node<AnyType> beginMarker;
    // 尾节点
    private Node<AnyType> endMarker;
    // 同步计数
    private int modCount;

    /**
     * 初始化一个空链表
     */
    public MyLinkedList() {
        beginMarker = new Node(null, null, null);
        endMarker = new Node(null, null, beginMarker);
        beginMarker.next = endMarker;
        modCount = 0;
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    /**
     * 空链表的定义是只有头尾节点
     */
    @Override
    public boolean isEmpty() {
        return beginMarker.next == endMarker;
    }

    @Override
    public void clear() {
        doClear();
    }

    /**
     * 判断链表是否包含某元素
     */
    @Override
    public boolean contains(AnyType x) {
        Node<AnyType> node = beginMarker;
        for (int i = 0, n = size(); i < n; i++) {
            node = node.next;
            if (node.data == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加节点在链表尾部
     * 不调用通用方法，因为插入地方的左右节点已经得到
     */
    @Override
    public void addObject(AnyType x) {
        Node<AnyType> newNode = new Node<>(x, endMarker, endMarker.prev);
        endMarker.prev.next = newNode;
        endMarker.prev = newNode;
        modCount++;
        size++;
    }

    @Override
    public void add(int idx, AnyType x) {
        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }
        // idx=0时得到的是beginMarker.next
        Node<AnyType> node = getNode(idx);
        Node<AnyType> newNode = new Node<>(x, node, node.prev);
        node.prev.next = newNode;
        node.prev = newNode;
        modCount++;
        size++;
    }

    @Override
    public boolean removeObject(AnyType x) {
        Node<AnyType> node = beginMarker;
        while(node.next != endMarker) {
            node = node.next;
            if (node.data == x) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<AnyType> node = getNode(idx);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        modCount++;
    }

    @Override
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    @Override
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> node = getNode(idx);
        AnyType oldVal = node.data;
        node.data = newVal;
        return oldVal;
    }

    public void printList() {
       Node<AnyType> node = beginMarker.next;
        System.out.println("*****开始显示链表元素*****");
       while (node != endMarker) {
           System.out.print(node.data + " ");
           node = node.next;
       }
        System.out.println();
        System.out.println("**************************");
    }

    // 3.18
    public void addFirst(AnyType x) {
        add(0, x);
    }

    public void addLast(AnyType x) {
        add(size(), x);
    }

    public boolean removeFirst() {
        remove(0);
        return true;
    }

    public boolean removeLast() {
        remove(size());
        return true;
    }

    public AnyType getFirst() {
        return get(0);
    }

    public AnyType getLast() {
        return get(size() - 1);
    }

    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<AnyType> {

        // 进行同步的计数

        private int expectedModCount;
        private Node<AnyType> current;
        private boolean okToRemove;
        // 调用next之后的值
        private AnyType lastVisitedVal;
        public Iterator() {
            expectedModCount = modCount;
            current = beginMarker.next;
        }

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastVisitedVal = current.data;
            current = current.next;
            okToRemove = true;
            return lastVisitedVal;
        }

        @Override
        public void remove() {
            if (expectedModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.removeObject(lastVisitedVal);
            expectedModCount++;
            okToRemove = false;
        }

    }

    public java.util.Iterator<AnyType> reverseIterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements java.util.Iterator<AnyType> {

        // 进行同步的计数

        private int expectedModCount;
        private Node<AnyType> current;
        // 调用next之后的值
        private AnyType lastVisitedVal;
        public ReverseIterator() {
            expectedModCount = modCount;
            current = endMarker.prev;
        }

        @Override
        public boolean hasNext() {
            return current != beginMarker;
        }

        @Override
        public AnyType next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastVisitedVal = current.data;
            current = current.prev;
            return lastVisitedVal;
        }

        @Override
        public void remove() {
            if (expectedModCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (lastVisitedVal == null) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.removeObject(lastVisitedVal);
            expectedModCount++;
            lastVisitedVal = null;
        }
    }


    public java.util.ListIterator<AnyType> listIterator() {
        return new ListIterator();
    }
    private class ListIterator implements java.util.ListIterator<AnyType> {


        private int expectedModCount = modCount;
        private Node<AnyType> current = beginMarker.next;
        private int index= 0;
        private Node<AnyType> lastReturned;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current = current == beginMarker ? beginMarker.next : current;
            current = current.next;
            index++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return current != beginMarker;
        }

        @Override
        public AnyType previous() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturned = current = current == endMarker ? endMarker.prev : current;
            current = current.prev;
            index--;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (current == lastReturned.next) {
                index--;
            }
            lastReturned.next.prev = lastReturned.prev;
            lastReturned.prev.next = lastReturned.next;
            lastReturned = null;
        }

        @Override
        public void set(AnyType anyType) {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.data = anyType;
        }

        @Override
        public void add(AnyType anyType) {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            MyLinkedList.this.add(index, anyType);
        }
    }
    /**
     * 清空让头尾节点直接互联  剩余的对象由于没有引用，会被GC
     */
    private void doClear() {
        modCount++;
        beginMarker.next = endMarker;
        endMarker.prev = beginMarker;
        size = 0;
    }

    /**
     * 根据索引得到节点对象
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0 ,size());
    }

    /**
     * 根据双链表可以左右遍历来得到节点
     */
    private Node<AnyType> getNode(int idx, int low, int high) {
        if (idx < low || idx > high) {
            throw new IndexOutOfBoundsException();
        }
            if (idx < size() / 2) {
                Node<AnyType> node = beginMarker.next;
                for (int i = 0; i < idx; i++) {
                    node = node.next;
                }
                return node;
            } else {
                // 索引为size()的节点是endMarker
                Node<AnyType> node = endMarker;
                for (int i = size(); i > idx; i--) {
                    node = node.prev;
                }
                return node;
            }
    }
}
