package cn.zhanguozhi.ADT;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class MyLinkedListWithLazyDeletion<AnyType> implements IMyList<AnyType> {

    private static class Node<AnyType> {
        private AnyType data;
        private boolean isDeleted;
        private Node<AnyType> prev;
        private Node<AnyType> next;

        private Node(AnyType data, boolean isDeleted, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.isDeleted = isDeleted;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<AnyType> first;
    private Node<AnyType> last;
    // 显示的元素个数
    private int size;
    private int modCount;
    // 被lazy delete的元素个数
    private int deletedSize;

    public MyLinkedListWithLazyDeletion() {
        first = new Node<>(null, false, null, null);
        last = new Node<>(null, false, first, null);
        first.next = last;
        size = 0;
        modCount = 0;
        deletedSize = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        doClear();
    }

    private void doClear() {
        first.next = last;
        last.prev = first;
        size = 0;
        deletedSize = 0;
        modCount++;
    }


    @Override
    public boolean contains(AnyType x) {
        Node<AnyType> node = first;
        while (node != last) {
            if (x == node.data && !node.isDeleted) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 在node前添加x
     */
    private void linkBefore(Node<AnyType> node, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, false, node.prev, node);
        if (node.prev == null) {
            // first节点
            first = newNode;
        } else {
            node.prev.next = newNode;
        }
        node.prev = newNode;
        size++;
        modCount++;
    }


    @Override
    public void addObject(AnyType x) {
        linkBefore(last, x);
    }

    @Override
    public void add(int idx, AnyType x) {
        // getNode中对于idx的判断不包括size()
        if (idx == size()) {
            linkBefore(last, x);
        } else {
            Node<AnyType> node = getNode(idx);
            linkBefore(node, x);
        }
    }

    @Override
    public boolean removeObject(AnyType x) {
        Node<AnyType> node = getNode(x);
        if (node == null) {
            // 没有含有x的节点
            return false;
        } else {
            node.isDeleted = true;
            deletedSize++;
            size--;
            modCount++;
            removeDeleted(deletedSize);
            return true;
        }

    }


    /**
     * 如果lazy delete元素的个数超过没有被删除的元素的个数  将懒惰删除的元素真正删掉
     */
    @SuppressWarnings("all")
    private void removeDeleted(int deletedSize) {
        if (deletedSize >= size) {
            // 链表中被lazy delete的元素和显示的元素一样多  需要清空lazy delete元素
            Node<AnyType> node = first.next;
            Node<AnyType> next = node.next;
            while (node != last) {
                if (node.isDeleted) {
                    removeNode(node);
                }
                node = next;
                next = next.next;
            }
            deletedSize = 0;
        }
    }

    @Override
    public void remove(int idx) {
        Node<AnyType> node = getNode(idx);
        if (node != null) {
            node.isDeleted = true;
            deletedSize++;
            size--;
            modCount++;
            removeDeleted(deletedSize);
        }
    }

    /**
     * 删除指定节点
     */
    private void removeNode(Node<AnyType> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // help GC
        node.prev = null;
        node.next = null;
    }
    @Override
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * 按照idx获取node，如果节点已经被lazy delete，就忽视
     * 由于是双链表  根据idx，进行从前往后还是从后往前查找
     */
    private Node<AnyType> getNode(int idx) {
        checkIdx(idx);
        if (idx < size() / 2) {
            Node<AnyType> node = first;
            for (int i = 0; i <= idx; ) {
                node = node.next;
                if (!node.isDeleted) {
                    i++;
                }
            }
            return node;
        } else {
            Node<AnyType> node = last;
            for (int i = size(); i > idx; ) {
                node = node.prev;
                if (!node.isDeleted) {
                    i--;
                }
            }
            return node;
        }
    }

    /**
     * 按照元素值找符合条件的第一个节点  如果没找到，返回null
     */
    private Node<AnyType> getNode(AnyType x) {
        if (first.next == last) {
            return null;
        }
        Node<AnyType> node = first.next;
        while (node != null) {
            if (!node.isDeleted && x == node.data) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    // 检查idx参数是否符合条件
    private void checkIdx(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> node = getNode(idx);
        AnyType val = node.data;
        node.data = newVal;
        return val;
    }

    public void printList() {
        System.out.println("***start to print list***");
        Node<AnyType> node = first.next;
        while (node != last) {
            if (!node.isDeleted) {
                System.out.print(node.data + " ");
            }
            node = node.next;
        }
        System.out.println();
        System.out.println("***print list over***");
    }
    @Override
    public java.util.Iterator<AnyType> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<AnyType> {

        private Node<AnyType> node = first.next;
        private int expectedModCount = modCount;
        private Node<AnyType> lastVisited;
        // 指向下一个没有被懒惰删除的节点
        private Node<AnyType> next;
        @Override
        public boolean hasNext() {
            next = node.next;
            // 要访问的下一个节点在最后node==last时为null  所以要先判断next != null
            while (next != null && next.isDeleted) {
                next = next.next;
            }
            // 要访问的节点与最后一个节点不相同
            return node != last;
        }

        @Override
        public AnyType next() {
            checkConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType val = node.data;
            lastVisited = node;
            node = next;
            return val;
        }

        @Override
        public void remove() {
            checkConcurrentModification();
            if (lastVisited == null) {
                // 调用remove例程前尚未调用next例程
                throw new IllegalStateException();
            }
            lastVisited.isDeleted = true;
            deletedSize++;
            expectedModCount++;
            MyLinkedListWithLazyDeletion.this.removeDeleted(deletedSize);
            modCount++;
            size--;
        }

        private void checkConcurrentModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
