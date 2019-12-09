package cn.zhanguozhi.ADT;

import java.util.NoSuchElementException;

public class StackImplByLinkedList<AnyType> {

    // 单链表
    private static class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;

        private Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    // 头节点 也装填数据
    private Node<AnyType> first;
    // 记录stack中元素个数（为了让size()方法以O(1)运行）
    private int size;

    public StackImplByLinkedList() {
        first = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        size = 0;
        first = null;
    }

    public void push(AnyType x) {
        addFirst(x);
    }

    private void addFirst(AnyType x) {
        // stack为空
        if (first == null) {
            first = new Node<>(x, null);
        } else {
            Node<AnyType> newNode = new Node<>(x, first);
            first = newNode;
            size++;
        }
    }

    public AnyType pop() {
        return deleteFirst();
    }

    private AnyType deleteFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        AnyType returnedVal = first.data;
        first = first.next;
        size--;
        return returnedVal;
    }

    public AnyType top() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    // 打印stack
    public void printStack() {
        System.out.println("***start to print stack***");
        Node<AnyType> node = first;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
        System.out.println("***print stack over***");
    }
}
