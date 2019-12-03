package cn.zhanguozhi.chapter2_BigO;

import java.math.BigInteger;

public class _4_NumPower {

    public static long pow(long num, long x) {
        if (x == 0) {
            return 1;
        } else if (x == 1) {
            return num;
        }
//        if (num == 0) {
//            return 0;
//        } else if (num == 1) {
//            return 1;
//        }
        if (x % 2 == 0) {
           //  System.out.println(num * num + " " + x / 2);
            return pow(num * num, x / 2);
        } else {
            // System.out.println(num * num + " " + x / 2);
            return pow(num * num, x / 2) * num;
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 10));
    }
}
