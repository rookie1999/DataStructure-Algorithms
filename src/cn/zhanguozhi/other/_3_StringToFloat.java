package cn.zhanguozhi.other;


/**
 * @author zhanguozhi
 * @date 2019.11.28
 * @desc 字符串转化为浮点数
 *       算法思想： 先根据小数点进行切割，然后得到整数部分和小数部分的字符数组
 *       对于整数部分：数组末尾是10的0次，每往前一个，就需要乘以一个10
 *       对于小数部分：数组开头是10的-1次，每往后一个，就需要除以10
 *        对于每个数组需要乘以的10的次幂，通过两个函数得到，一个是对于整数部分的得到是正次幂，另一个是小数部分的负次幂
 */
public class _3_StringToFloat {

    // 如果有小数点，则按小数点进行切割
    // 没有小数点，就当成一个整数
    public static float parseFloat(String s) {
        if (s.contains(".")) {
            String[] str = s.split("\\.");
            return big(str[0]) + small(str[1]);
        } else {
            return big(s);
        }
    }

    private static float big(String s) {
        char[] chars = s.toCharArray();
        float res = 0;
        int len = s.length() - 1;
        for (int i = len; i >= 0; i--) {
            res += getTen(len - i) * (chars[i] - 48);
        }
        return res;
    }

    // 对于整数部分
    private static int getTen(int count) {
        int sum = 1;
        for (int i = 0; i < count; i++) {
            sum *= 10;
        }
        return sum;
    }

    // 对于小数部分
    private static float getOverTen(int count) {
        float sum = 0.1f;
        for (int i = 0; i < count; i++) {
            sum /= 10;
        }
        return sum;
    }
    private static float small(String s) {
        char[] chars = s.toCharArray();
        float res = 0;
        int len = s.length() - 1;
        for (int i = 0; i <= len; i++) {
            res += getOverTen(i) * (chars[i] - 48);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(parseFloat("3468.57"));
        System.out.println(parseFloat("3468"));
    }
}
