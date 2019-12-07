package cn.zhanguozhi.chapter3_Connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/**
 * @author zhanguozhi
 * @date 2019.12.6
 * @desc 打印链表
 */
public class _3_1_PrintLots {

    /**
     *
     * @param L  要被打印的链表
     * @param P  升序排列的整数链表，其元素值即为L链表的索引值
     * @param <E>
     */
    public static <E> void  printLots(List<E> L, List<Integer> P) {
        if (L == null || P == null) {
            throw new IllegalArgumentException();
        }
        // P元素想要打印L的值超过L的范围
        if (P.get(P.size() - 1) > L.size()) {
            throw new IllegalStateException();
        }
        System.out.println("开始打印L链表的选中元素");
        // 根据是否支持随机访问来选择最佳访问方式
        if (P instanceof RandomAccess) {
            for (int i = 0, n = P.size(); i < n; i++) {
                System.out.print(L.get(P.get(i)) + " ");
            }
        } else {
            for (Iterator<Integer> iter = P.iterator(); iter.hasNext();) {
                System.out.print(L.get(iter.next()) + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Character> list1 = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            list1.add((char) i);
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(8);
        list2.add(16);
        list2.add(25);
        printLots(list1, list2);
    }
}
