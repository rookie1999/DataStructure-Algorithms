package cn.zhanguozhi.chapter1_Introduction;

/**
 * @author zhanguozhi
 * @date 2019.11.20
 * @desc 每次只能打印一个数字  递归打印一个double类型的数
 *         算法核心： printDouble  1.首先将double类型的数按照小数点分离
 *                                2.先打印整数部分  输出小数点  最后输出小数部分
 *         收获： 用算法解决问题  不一定非要使用一个方法就能解决  有时可以将要重又的部分继续提取成一个方法
 */
public class _1_3_PrintDigit {
    public static void printDouble(double num) {
        // 一个double类型的数 % 10 等于其个位数和小数的和
        if (num < 0) {
            System.out.print("-");
            num = Math.abs(num);
        }
        String s = num + "";
        String[] digits = s.split("\\.");
        printInt(Integer.parseInt(digits[0]));
        System.out.print(".");
        printInt(Integer.parseInt(digits[1]));
    }

    private static void printInt(int num) {
        if (num > 10) {
            printInt(num / 10);
        }
        System.out.print(num % 10);
    }

    public static void main(String[] args) {
        printDouble(122.45);
        System.out.println();
        printDouble(-122.45);
    }
}
