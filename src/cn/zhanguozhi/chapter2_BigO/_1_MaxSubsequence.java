package cn.zhanguozhi.chapter2_BigO;

public class _1_MaxSubsequence {

    private static int[] arr = {4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2, 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2
    , 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2, 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2, 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2
    , 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2, 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2, 4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2
    };

    // O(N^2)
    public static int maxSubsequence1(int[] arr) {
        int maxSum = 0; // 如果数组元素都是负数，则最大子序列为空，此时最大值是0
        for (int i = 0; i < arr.length; i++) {
            int thisSum = 0;
            for (int j = i; j < arr.length; j++) { // 每一轮都是从i到末尾的所有子数组的和
                thisSum += arr[j];
                if (thisSum > maxSum) { // 每一次循环都是一个子数组被求和 需要进行判断
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubsequence2(int[] arr) {
        return maxSubSequence2Real(arr, 0, arr.length - 1);
    }
    private static int maxSubSequence2Real(int[] arr, int left, int right) {
        if (left == right) { // base case 如果left和right相等，则是一个元素
            // 该元素大于0 返回数组中的值  如果小于0 则不选中
            return arr[left] < 0 ? 0 : arr[left];
        }
        // 递归左半部分和右半部分
        int center = (left + right) / 2;
        int maxLeftSum = maxSubSequence2Real(arr, left, center);
        int maxRightSum = maxSubSequence2Real(arr, center + 1, right);

        // 计算中间
        int maxLeftMiddleSum = 0; // 从中间开始的左半部分的最大值
        int leftMiddleSum = 0;  // 循环中的变量
        int maxRightMiddleSum = 0;
        int rightMiddleSum = 0;
        for (int i = center; i >= left; i--) {
            leftMiddleSum += arr[i];
            if (leftMiddleSum > maxLeftMiddleSum) {
                maxLeftMiddleSum = leftMiddleSum;
            }
        }
        for (int i = center + 1; i <= right; i++) {
            rightMiddleSum += arr[i];
            if (rightMiddleSum > maxRightMiddleSum) {
                maxRightMiddleSum = rightMiddleSum;
            }
        }
        return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftMiddleSum + maxRightMiddleSum);
    }


//    private static int max(int a, int b, int c) {
//        if (a > b) {
//            if (b > c) {
//                return a;
//            } else {
//                return a > c ? a :c;
//            }
//        } else {
//            if (a > c) {
//                return b;
//            } else {
//                return b > c ? b : c;
//            }
//        }
//    }
    public static void main(String[] args) {
        long start1 = System.nanoTime();
        int res1 = maxSubsequence1(arr);
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        int res2 = maxSubsequence2(arr);
        long end2 = System.nanoTime();
        long start3 = System.nanoTime();
        int res3 = maxSubsequence3(arr);
        long end3 = System.nanoTime();
        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
        System.out.println(end3 - start3);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    /**
     * 这种是最优的算法，并且可以在数据输入的任意时刻给出当前的最大连续子序列的和
     * 算法核心： 如果一个数是负数，那么它不可能是一个子序列的开头
     *            对于一个子序列如果它的和是负数，那么从起始位置i到终止位置j中，选择任意一个位置p，i到p-1的序列和大于等于p到j的序列和
     *            （因为子序列和是负数，且开头是正数）
     *            所以可以将i直接推进到j+1之后重新计算最大子序列的和
     */
    public static int maxSubsequence3(int[] arr) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i : arr) {
            thisSum += i;
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

}
