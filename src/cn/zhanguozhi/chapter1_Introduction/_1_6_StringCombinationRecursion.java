package cn.zhanguozhi.chapter1_Introduction;

/**
 * @author zhanguozhi
 * @date 2019.11.21
 * @desc 程序：输入一个字符串  得到该字符串的所有组合
 *              比如： "abc"  结果："", "a", "b", "c", "ab", "bc", "ac", "abc"
 *          算法核心：
 *              对于字符串上的每一个字符  都有两种选择  要么在字符序列中  要么不在字符序列中
 *              所以每次递归调用会出现两个  一个是当前索引没有加入字符序列 一个是当前索引加入字符序列
 *              然后对下一个索引进行操作
 */
public class _1_6_StringCombinationRecursion {

    public static void combine(String str) {
        if (str == null) {
            throw new RuntimeException("字符串为null");
        }
        String res = "";
        combine(str, res, 0);
    }

    public static void combine(String str, String result, int index) {
        if (index == str.length()) { // 如果str="" result在这里就会打印""并返回
            System.out.println(result);
            return;
        }
        combine(str, result, index + 1);
        combine(str, result + str.substring(index, index + 1), index + 1);
    }

    public static void main(String[] args) {
        combine("abcd");
    }
}
