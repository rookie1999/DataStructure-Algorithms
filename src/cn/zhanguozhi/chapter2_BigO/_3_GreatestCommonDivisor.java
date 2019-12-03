package cn.zhanguozhi.chapter2_BigO;

/**
 * @author zhanguozhi
 * @date 2019.11.29
 * @desc 欧几里得算法核心：
 *          a = kb + r
 *          1）a和b的约数是b和r的约数 a = su, b = tu, r = a -kb = su - tub = (s - tb)u
 *          2)b和r的约数是a和b的约数 b = sv, r = tv, a = ksv + tv = (ks + t)v
 *          因此，a和b的约数集合就是b和r的约数集合，即gcd(a, b) = gcd(b, a % b)
 *
 *          算法中如果a<b，会在第一次进行自动交换
 */
public class _3_GreatestCommonDivisor {

    public static int getGreatestCommonDivisor1(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGreatestCommonDivisor1(b, a % b);
    }

    public static int getGreatestCommonDivisor2(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor1(12, 36));
        System.out.println(getGreatestCommonDivisor2(12, 36));
    }
}
