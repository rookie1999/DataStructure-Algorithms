package cn.zhanguozhi.other;

/**
 * @author zhanguozhi
 * @date 2019.11.20
 * @desc 这个算法是求平方根算法的一种解法
 *          其核心思想使用的是牛顿迭代法
 *          res = （lastRes + num / lastRes） / 2
 *              下面是对牛顿迭代法求解平方根进行证明：
 *                  对于一个数N，它的平方根在f(x) = x^2 - N上面的一个点(x1,0)
 *                  求(x3,x3^2 - N)点的切线y = f'(x3) * (x - x3) + x^3 - N 该线与x轴的交点x2满足 0 = 2 * x3 * (x2 - x3) + x3^2 - N
 *                  则x2 = (x3 + N/x3) / 2
 *                  得到x2之后，取f(x)上x=x2这个点继续上述过程，最终会逼近x1，也就是平方根
 *
 *         代码的终止条件可以是两次的交点的误差在1e-6次以内
 *
 */
public class _1_sqrt {
    public static double sqrt(double num) {
        if (num < 0) {
            throw new RuntimeException("负数开什么方");
        }
        if (num == 0) {
            return 0;
        }
        final double jingDu = 1e-6;
        double data = num;
        double res = 0;
        double lastRes = Double.MAX_VALUE;
        do {
            res = (data + num / data) / 2;
            lastRes = Math.abs(res - data);
            data = res;
        } while (lastRes > jingDu);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(3));
        System.out.println(Math.sqrt(3));
        System.out.println(sqrt(2));
        System.out.println(Math.sqrt(2));
    }
}
