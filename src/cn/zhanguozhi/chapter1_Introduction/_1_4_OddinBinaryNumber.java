package cn.zhanguozhi.chapter1_Introduction;

import java.sql.SQLOutput;

/**
 * @author zhanguozhi
 * @date 2019.11.20
 * @desc 算法描述：计算出一个数的二进制形式中有多少个1
 *        算法思想：如果一个数是奇数 则二进制末尾一定为0
 */
public class _1_4_OddinBinaryNumber {

    /**
     * recursion
     * 递归实现
     * 1.基准情形：如果num为0，则二进制为0 没有奇数可以查找
     * 2.不断推进：如果当期的数是偶数，则二进制末尾是0，消除这一位之后继续查找，所以返回二进制右移一位的结果
     *             如果当前为奇数，则二进制末尾是1，结果中需要包含1和二进制右移一位的结果
     * 3.合成效益法则：没有重复计算的变量
     */
    public static int oddRecursion(int num) {
//        if (num == 0) {
//            return 0;
//        }
//        if (num % 2 == 0) {
//            return oddRecursion(num >> 1);
//        } else {
//            return oddRecursion(num >> 1) + 1;
//        }
        // -----------上下两种递归都可以  只是下面这种更简洁--------------
        if (num < 2) {
            return num;
        }
        return num % 2 + oddRecursion(num >> 1);
    }

    /**
     * 经过验证 递归效率最低 直接用数字循环效率最高
     */
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(233));
        long start1 = System.nanoTime();
        System.out.println(oddRecursion(32675649));
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        System.out.println(oddLoop(32675649));
        long end2 = System.nanoTime();
        long start3 = System.nanoTime();
        System.out.println(oddLoopString(32675649));
        long end3 = System.nanoTime();
        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
        System.out.println(end3 - start3);
    }

    /**
     * 循环 直接判断最后一位
     */
    public static int oddLoop(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    /**
     * 循环 将数字转化为二进制字符串之后 判断每一个位上的字符是否为'1'
     */
    public static int oddLoopString(int num) {
        int count = 0;
        String str = Integer.toBinaryString(num);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
