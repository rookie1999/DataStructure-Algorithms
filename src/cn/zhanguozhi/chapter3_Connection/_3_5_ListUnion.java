package cn.zhanguozhi.chapter3_Connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("all")
public class _3_5_ListUnion {

    public static<E extends Comparable<? super E>> List<E> union(List<E> list1, List<E> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        if (list1.size() == 0) {
            return list2;
        }
        List<E> res = new ArrayList<>(list1);
        for (Iterator<E> iter2 = list2.iterator(); iter2.hasNext(); ){
            E val = iter2.next();
            if (!list1.contains(val)) {
                res.add(val);
            }
        }
        return res;
    }

    public static<E extends Comparable<? super E>> List<E> union2(List<E> list1, List<E> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        if (list1.size() == 0) {
            return list2;
        }
        List<E> res = new ArrayList<>();
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();
        E item1 = iter1.next();
        E item2 = iter2.hasNext() ? iter2.next() : null;
        while (item1 != null && item2 != null) {
            int comp = item1.compareTo(item2);
            if (comp < 0) {
                res.add(item1);
                item1 = iter1.hasNext() ? iter1.next() : null;
            } else if (comp > 0) {
                res.add(item2);
                item2 = iter2.hasNext() ? iter2.next() : null;
            } else {
                // 相等时  两个元素都没有在集合中
                res.add(item1);
                item1 = iter1.hasNext() ? iter1.next() : null;
                item2 = iter2.hasNext() ? iter2.next() : null;
            }
        }
       if (item1 != null) {
           // 因为item2 = null 导致上一个while循环结束 但是结束的时候item1的值没有添加
           res.add(item1);
           while (iter1.hasNext()) {
               res.add(iter1.next());
           }
       } else {
           // 同上
           res.add(item2);
           while (iter2.hasNext()) {
               res.add(iter2.next());
           }
       }
        return res;
    }
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int i = 2; i <= 10; i+=2) {
            list2.add(i);
        }
        List<Integer> res = union(list1, list2);
        res.forEach(System.out::println);
        System.out.println("--------");
        res = union2(list1, list2);
        res.forEach(System.out::println);

    }
}
