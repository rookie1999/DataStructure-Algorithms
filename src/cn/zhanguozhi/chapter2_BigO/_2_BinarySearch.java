package cn.zhanguozhi.chapter2_BigO;

import java.util.Arrays;

public class _2_BinarySearch {
    private static Integer[] arr = {4, -1, 8, -2, 3, 6, 11, -20, -5, 3, 1, 7, 9, -10, 2};

    public static<AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] arr, AnyType x) {
        int min = 0;
        int max = arr.length - 1;
        int start;
        while (min <= max) {
            start = (min + max) / 2;
            //System.out.println(start);
            if (arr[start].compareTo(x) < 0) {
                min = start + 1;
            } else if (arr[start].compareTo(x) > 0) {
                max = start - 1;
            } else {
                return start;
            }
        }
        return -min;
    }

    public static void main(String[] args) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, -9));
    }
}
