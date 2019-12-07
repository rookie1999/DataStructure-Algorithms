package cn.zhanguozhi.chapter3_Connection;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author zhanguozhi
 * @date 2019.12.7
 * @desc 约瑟夫环的问题
 *          课后答案有错误 每次迭代都需要判断迭代器是否有效
 */
public class _3_6_Josephus {

    // O(N^2)
    public static int josephus(int m, int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 链表存储从1开始
            list.add(i + 1);
        }
        // 需要被删除的角标
        int index = 0;
        System.out.println("被删除的顺序：");
        while (list.size() != 1) {
            // 每次更新需要删除的角标索引
            index = (index + m) % list.size();
            System.out.print(list.get(index) + " ");
            list.remove(index);
        }
        System.out.println();
        return list.getFirst();
    }

    // 上面那种算法没有充分利用到链表的
    public static int josephus2(int m, int n) {
        // i,j是循环句柄 myPrime是每次要删除的索引 numLeft是链表中元素个数
        int i, j, myPrime, numLeft;
        LinkedList<Integer> num = new LinkedList<>();
        for (i = 0; i < n; i++) {
            num.add(i + 1);
        }
        numLeft = n;
        myPrime = m % n;
        ListIterator<Integer> iter = num.listIterator();
        // 每次删除的元素
        Integer item = 0;
        System.out.println("被删除的顺序:");
        for (i = 1; i < n; i++) {
            // 每次的数都是在原来迭代的基础上继续迭代
            myPrime = m % numLeft;
            if (myPrime <= numLeft / 2) {
                // 这一步是使从迭代从当前位置开始
                // 因为迭代器的next是返回current，而previous是返回current.prev
                // 这里的话课后答案是错误的，因为其内部的cursor由于上次迭代已经到了最后的位置，导致item没有更新
                // 所以和下面一样，每次迭代都必须判断迭代器是否还有效
                if (iter.hasNext()) {
                    item = iter.next();
                } else {
                    iter = num.listIterator();
                    if (iter.hasNext()) {
                        item = iter.next();
                    }
                }
                for (j = 0; j < myPrime; j++) {
                    // 如果迭代器失效  就重新生成一个
                    if (!iter.hasNext()) {
                        iter = num.listIterator();
                    }
                    item = iter.next();
                }
            } else {
                for (j = 0; j < numLeft - myPrime; j++) {
                    // 迭代器不生效 说明已达到最前端 通过构造器重载赋予内部cursor值
                    if (!iter.hasPrevious()) {
                        iter = num.listIterator(num.size());
                    }
                    item = iter.previous();
                }
            }
            System.out.print(item + " ");
            iter.remove();
            numLeft--;
        }
        System.out.println();
        return num.get(0);
    }
    public static void main(String[] args) {
        int num =josephus(0, 5);
        System.out.println(num);
        long start1 = System.nanoTime();
        num = josephus(1, 5);
        long end1 = System.nanoTime();
        System.out.println(num);
        System.out.println("第一种算法使用时间：" + (end1 - start1));
        num = josephus2(0, 5);
        System.out.println(num);
        long start2 = System.nanoTime();
        num = josephus2(1, 5);
        long end2 = System.nanoTime();
        System.out.println(num);
        System.out.println("第二种算法使用时间：" + (end2 - start2));
    }
}
