package cn.zhanguozhi.chapter3_Connection;

// ctrl + alt + o ： 优化导入的包
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhanguozhi
 * @date 2019.12.6
 * @desc 有序链表的交集
 */
public class _3_4_ListIntersection {

    /**
     * 没有使用迭代器的版本
     * @param a 需要合并的有序链表a
     * @param b 需要合并的有序链表b
     * @param <E> 链表的类型  因为要比较元素  所以该类型是继承自Comparable或者该类的基类继承自Comparable
     * @return  返回链表的交集
     */
    public static<E extends Comparable<? super E>> List<E> intersect(List<E> a, List<E> b) {
        // 链表为空 参数异常
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        // 有链表长度为0 则返回空链表
        if (a.size() == 0 || b.size() == 0) {
          return Collections.emptyList();
        }
        List<E> res = new ArrayList<>();
        // 没有选用迭代器的原因是每次进行数据访问的时候都会自动迭代一个位置
        // 但这是不可取的 因为每次比较只有上一次元素相等的时候才能两个链表同时迭代
       for (int i = 0, j = 0, size1 = a.size(), size2 = b.size();i != size1 && j != size2;) {
            E val1 = a.get(i);
            E val2 = b.get(j);
            int comp = val1.compareTo(val2);
            if (comp < 0) {
                i++;
            } else if (comp > 0){
                j++;
            } else {
                // 元素相等 两个指针同时迭代
                res.add(val1);
                i++;
                j++;
            }
        }
        return res;
    }

    /**
     * 使用迭代器的版本
     * @param list1
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> List<E> intersect2(List<E> list1, List<E> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        if (list1.size() == 0 || list2.size() == 0) {
            return Collections.emptyList();
        }
        List<E> res = new ArrayList<>();
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();
        // 可以直接获取下一个元素  因为已经在上面判断了链表不为空
        E item1 = iter1.next();
        E item2 = iter2.next();
        while (item1 != null && item2 != null) {
            int comp = item1.compareTo(item2);
            if (comp < 0) {
                item1 = iter1.hasNext() ? iter1.next() : null;
            } else if (comp > 0) {
                item2 = iter2.hasNext() ? iter2.next() : null;
            } else {
                res.add(item1);
                item1 = iter1.hasNext() ? iter1.next() : null;
                item2 = iter2.hasNext() ? iter2.next() : null;
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
        long start1 = System.nanoTime();
        List<Integer> res = intersect(list1, list2);
        long end1 = System.nanoTime();
        res.forEach(System.out::println);
        System.out.println("第一种算法用时时间：" + (end1- start1));
        long start2 = System.nanoTime();
        res = intersect2(list1, list2);
        long end2 = System.nanoTime();
        res.forEach(System.out::println);
        System.out.println("第二种算法用时时间:" + (end2 - start2));
    }
}
