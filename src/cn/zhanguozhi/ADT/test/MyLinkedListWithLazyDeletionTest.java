package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.MyLinkedListWithLazyDeletion;

import java.util.Iterator;

public class MyLinkedListWithLazyDeletionTest {

    public static void main(String[] args) {
        MyLinkedListWithLazyDeletion<Integer> list = new MyLinkedListWithLazyDeletion<>();
        System.out.println("size:" + list.size());
        System.out.println("isEmpty:" + list.isEmpty());
        for (int i = 1; i < 10; i++) {
            list.addObject(i);
        }
//        list.add(0,0);
//        list.add(0,0);
//        list.add(0,0);
        list.add(list.size() -1,0);
        list.add(list.size(),0);
        list.add(list.size(),0);
        list.remove(list.size() - 1);
        list.remove(list.size() - 3);
        list.printList();
        System.out.println("contains:" + list.contains(7));
        System.out.println("size:" + list.size());
        System.out.println("isEmpty:" + list.isEmpty());
        System.out.println("get 3:" + list.get(3));
        list.set(list.size() - 1, 10);
        list.removeObject(10);
        list.printList();
        System.out.println("before delete size:" + list.size());
//        for (int i = 0; i < 9; i++) {
//            list.remove(0);
//        }
        list.removeObject(7);
        System.out.println("contains:" + list.contains(7));
        for (Iterator<Integer> iter = list.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("after delete size:" + list.size());
        System.out.println("isEmpty:" + list.isEmpty());
        list.printList();
    }
}
