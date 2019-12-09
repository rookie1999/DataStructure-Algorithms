package cn.zhanguozhi.ADT;

import java.util.NoSuchElementException;

/**
 * stack(LIFO)的两种常见实现之一：Array
 * 将元素装入一个数组之中
 * index指针指向stack的最新添加的元素 -1代表stack为空
 * 为空时不能使用pop、top操作
 * 对于push的限制没有（实质上取决于系统的内存大小），允许添加元素的时候进行动态扩容
 * @author zhanguozhi
 * @date 2019.12.9
 */
@SuppressWarnings("unchecked")
public class StackImplByArray<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    private AnyType[] items;
    // 指向stack中最后一个元素（即新添加的元素）
    private int index;

    public StackImplByArray() {
        items = (AnyType[]) new Object[DEFAULT_CAPACITY];
        // -1 代表当前stack为空
        index = -1;
    }

    public int size() {
        return index + 1;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        index = -1;
    }

    public void push(AnyType x) {
        addLast(x);
    }

    public AnyType pop() {
        return deleteLast();
    }

    public AnyType top() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return items[index];
    }

    private void addLast(AnyType x) {
        if ((index + 1) == items.length) {
            ensureCapacity();
        }
        items[++index] = x;
    }

    private AnyType deleteLast() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return items[index--];
    }

    // 扩容
    private void ensureCapacity() {
        AnyType[] newItems = (AnyType[]) new Object[items.length + (items.length >> 1)];
//        for (int i = 0; i < items.length; i++) {
//            newItems[i] = items[i];
//        }
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    // 打印stack(顺序是先打印后添加的元素)
    public void printStack() {
        System.out.println("***started to print stack***");
        for (int i = index; i >= 0; i--) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
        System.out.println("***print stack over***");
    }

}
