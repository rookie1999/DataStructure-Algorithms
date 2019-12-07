package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        System.out.println(arrayList.size());
        System.out.println(arrayList.isEmpty());
//        arrayList.add(5);
//        arrayList.printList();
//        arrayList.remove(0);
//        arrayList.printList();
        arrayList.printList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(0, i);
        }
        arrayList.printList();
        arrayList.removeObject(7);
        arrayList.remove(7);
        System.out.println();
        arrayList.printList();
//        arrayList.remove(7);
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
//            arrayList.addObject(3);
            if (value == 5) {
                iterator.remove();
            } else {
                System.out.print(value + " ");
            }
        }
        System.out.println();
        arrayList.printList();
        arrayList.trimToSize();
        arrayList.printList();
        System.out.println(arrayList.size());
        System.out.println(arrayList.isEmpty());
        arrayList.clear();
        System.out.println(arrayList.isEmpty());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(9999);
        list.add(10000);
        arrayList.addAll(list);
        arrayList.printList();
//        arrayList.removeAll(list);
//        arrayList.printList();
        ListIterator<Integer> listIter = arrayList.listIterator(arrayList.size());
        while (listIter.hasPrevious()) {
            System.out.print(listIter.previous() + " ");
        }
        System.out.println();


    }

}
