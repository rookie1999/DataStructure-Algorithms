package cn.zhanguozhi.chapter1_Introduction;

/**
 * @author zhanguozhi
 * @date 2019.11.21
 * @desc 递归打印字符串的所有排列
 *          比如："abc"  结果：abc, acb, bac, bca, cab, cba
 *       算法核心：
 *          问题的实质是得到字符串的全排列
 *              对于一个字符数组，每次固定它的数组或子数组的首位(通过循环实现)
 *              然后递归固定它长度为当前数组减一的子数组的首位
 *              如果发现此时将要固定的位置是原数组的最后一个元素  就将该数组打印(终止条件)
 *
 *              如果想要进行去重 可以在交换之前进行判断  是否存在需要交换的位置的值和前面的值相等  如果存在一个相等则不进行交换和递归
 *              ！！！注意：每一个进行递归调用之后必须将之前交换的值换回来  否则去重会不彻底
 */
public class _1_5_StringPermutionRecursion {

    //得到字符串的不同排列
    public static void permute(String str) {
        permute(str.toCharArray(), 0, str.length() - 1);
    }

    //递归打印一个字符序列 (去重)
    public static void permute(char[] ch, int low, int high) {
        if (low == high) {
            System.out.println(new String(ch));
            return;
        }
        for (int i = low; i < ch.length; i++) {
            if (canSwap(ch, low, i)) {
                swap(ch, low, i); // 固定索引为low的字符
                permute(ch, low + 1, high); // 递归索引在low之后的字符序列
                swap(ch, low ,i); // 重置low的字符，让下一个进来
            }
        }
    }
//    //递归打印一个字符序列(但这种方法会打印重复字符串)
//    public static void permute(char[] ch, int low, int high) {
//        if (low == high) {
//            System.out.println(new String(ch));
//            return;
//        }
//        for (int i = low; i < ch.length; i++) {
//            swap(ch, low, i); // 固定索引为low的字符
//            permute(ch, low + 1, high); // 递归索引在low之后的字符序列
//            swap(ch, low ,i); // 重置low的字符，让下一个进来
//        }
//    }

    private static void swap(char[] ch, int from, int to) {
        char temp = ch[from];
        ch[from] = ch[to];
        ch[to] = temp;
    }

    private static boolean canSwap(char[] ch, int begin, int index) {
        for (int i = begin; i < index; i++) {
            if (ch[i] == ch[index]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        permute("abc");
        permute("ssss");
        permute("baac");
        System.out.println("--------");
        permute("abaa");  // 如果每次交换完进行递归之后不交换回来的话  对于这个用例会出现去重失败
    }
}
