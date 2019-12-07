package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.MyLinkedList;

import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        System.out.println("isEmpty:" + list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
        }
        Iterator<Integer> iterator = list.reverseIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        list.add(0, 0);
        list.remove(0);
        list.printList();
        System.out.println("size:" + list.size());
        System.out.println("isEmpty:" + list.isEmpty());
        list.set(5, 99);
        System.out.println("idx=5: " + list.get(5));
        System.out.println("contains(5):" + list.contains(5));
        System.out.println("contains(9):" + list.contains(9));
        list.removeObject(99);
        System.out.println("*****开始显示链表元素*****");
        ListIterator<Integer> iter = list.listIterator();
        for (; iter.hasNext();) {
            int val = iter.next();
            if (val == 2) {
                iter.remove();
            } else {
                System.out.print(val + " ");
            }
        }
        System.out.println();
        for (; iter.hasPrevious();) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        for (; iter.hasNext();) {
            int val = iter.next();
            if (val == 2) {
                iter.remove();
            } else {
                System.out.print(val + " ");
            }
        }
        System.out.println();
        list.clear();
        list.addObject(33);
        list.printList();
        list.addFirst(0);
        list.addLast(100);
        Iterator<Integer> iterator1 = list.reverseIterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();
    }
}
