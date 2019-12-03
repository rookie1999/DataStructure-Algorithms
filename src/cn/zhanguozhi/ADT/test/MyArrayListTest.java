package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.MyArrayList;

import java.util.Iterator;

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
            arrayList.addObject(3);
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
    }

}
