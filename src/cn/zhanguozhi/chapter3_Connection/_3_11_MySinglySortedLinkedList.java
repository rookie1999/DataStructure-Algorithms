package cn.zhanguozhi.chapter3_Connection;

public class _3_11_MySinglySortedLinkedList<AnyType extends Comparable<? super AnyType>> {

    private int size = 0;
    private Node<AnyType> first;

    public _3_11_MySinglySortedLinkedList() {
        first = new Node<>(null, null);
    }

    /**
     * Node类
     */
    private class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public void printList() {
        System.out.println("开始打印链表:");
        Node<AnyType> node = first.next;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public boolean contains(AnyType x) {
        if (first.next == null) {
            // 空链表
            return false;
        }
        Node<AnyType> node = first.next;
        while(node != null) {
            if (x.equals(node.data)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 没有使用contains方法，因为此添加可以在一轮里面完成  使用的话  浪费时间又遍历一遍
     */
    public void add(AnyType x) {
        Node<AnyType> node = first;
        if (first.next == null) {
            Node<AnyType> newNode = new Node<>(x, null);
            first.next = newNode;
            return;
        }
        // 这种情况是解决不允许出现重复元素的
        /*
        while (node.next != null) {
            int comp = x.compareTo(node.next.data);
            if (comp == 0) {
                // 如果相等 则链表已含有该元素
                return;
            } else if (comp < 0) {
                // 第一次小于的时候  说明node.next.data比x大 node.data比x小  node的下一个应该是节点插入的地方
                Node<AnyType> newNode = new Node<>(x, node.next);
                node.next = newNode;
                size++;
                return;
            }
            node = node.next;
        }
         */
        // 允许出现重复元素
        while (node.next != null && x.compareTo(node.next.data) >= 0) {
            node = node.next;
        }
        Node<AnyType> newNode = new Node<>(x, node.next);
        node.next = newNode;
        size++;
    }

    public boolean remove(AnyType x) {
        Node<AnyType> node = first;
        while(node.next != null) {
            if (x.equals(node.next.data)) {
                node.next = node.next.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public static void main(String[] args) {
        _3_11_MySinglySortedLinkedList<Integer> list = new _3_11_MySinglySortedLinkedList<>();
        for (int i = 10; i > 1; i--) {
            list.add(i);
        }
        list.add(6);
        list.printList();
        System.out.println(list.contains(6));
        System.out.println(list.contains(-1));
        list.remove(7);
        list.remove(8);
        list.printList();
    }
}
