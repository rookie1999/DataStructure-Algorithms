package cn.zhanguozhi.chapter1_Introduction;

/**
 * @author zhanguozhi
 * @date 2019.11.18
 * @desc this is an algorithm for selecting the k-th largest element from an array
 */
public class _1_1_K_sort {

    /**
     * test result: the algorithm designed is worse than the bubblesort algorithm
     */
    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 9, 7, 4, 2, 8};
        long start1 = System.nanoTime();
        int res1 = getKLargestElement_1(arr, 6);
        long end1 = System.nanoTime();
        System.out.println(end1 - start1);
        long start2 = System.nanoTime();
        int res2 = bubbleSortKLargest(arr, 6);
        long end2 = System.nanoTime();
        System.out.println(end2 - start2);
        System.out.println(res1);
        System.out.println(res2);

    }

    /**
     * the idea is to get a new array contains part of the origin elements
     * then let it be sorted
     * compare the kth element from the origin to the last element from the new array
     * if > , insert the kth element from the origin to the suitable location in the new array
     * @param arr
     * @param k
     * @return
     */
    public static int getKLargestElement_1(int[] arr, int k) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException();
        }
        int k_thElement;
        if (k <= arr.length / 2) {
            // k在数组的前半部分
            int[] newArr = new int[k];
            for (int i = 0; i < k; i++) {
                newArr[i] = arr[i];
            }
            // 冒泡排序，从大到小，数组最后一个元素是最小的
            bubbleSort(newArr, '+');
            for( int i = k; i < arr.length; i++) { // 如果比newArr中元素大，就插入到newArr中合适的地方
                if (arr[i] > newArr[k - 1]) {
                    newArr[k - 1] = arr[i];
                }
                for (int j = k - 1; j > 0; j --) {
                    if (newArr[j - 1] < newArr[j]) {
                        k_thElement = newArr[j - 1];
                        newArr[j - 1] = newArr[j];
                        newArr[j] = k_thElement;
                    }
                }
            }
            k_thElement = newArr[k - 1];
        } else {
            // k在数组的后半部分 第k大就是第(arr.length - k + 1)小
            int[] newArr = new int[arr.length - k + 1];
            int length = newArr.length;
            for (int i = 0; i < length; i++) {
                newArr[i] = arr[i];
            }
            // 冒泡排序，从小到大，数组最后一个元素是最大的
            bubbleSort(newArr, '-');
            k_thElement = newArr[length - 1];
            for( int i = length; i < arr.length; i++) {
                if (arr[i] < k_thElement) {
                    newArr[length - 1] = arr[i];
                    for (int j = length - 1; j > 0; j--) {
                        if (newArr[j - 1] > newArr[j]) {
                            k_thElement = newArr[j - 1];
                            newArr[j - 1] = newArr[j];
                            newArr[j] = k_thElement;
                        }
                    }
                }
            }
            k_thElement = newArr[length - 1];
        }
        return k_thElement;
    }

    /**
     * 冒泡排序
     * @param arr 要排序的数组
     * @param operator 如果是'+'，就从大到小，如果是'-'，从小到大
     */
    private static void bubbleSort(int[] arr, char operator) {
        if (arr == null) {
            throw new IllegalArgumentException("参数异常");
        }
        if (operator == '+') {
            // 从大到小
            int temp;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] < arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        } else if (operator == '-') {
            int temp;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static int bubbleSortKLargest(int[] arr, int k) {
        if (arr == null || k < 0 || k > arr.length) {
            throw new IllegalArgumentException("参数异常");
        }
        bubbleSort(arr, '+');
        return arr[k - 1];
    }
}
