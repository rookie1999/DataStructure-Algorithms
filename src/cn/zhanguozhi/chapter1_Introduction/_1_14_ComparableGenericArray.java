package cn.zhanguozhi.chapter1_Introduction;

public class _1_14_ComparableGenericArray<T extends Comparable> {

    private T[] array;
    private final int DEFAULT_SIZE = 5;
    private int size = 0; // 增删清空需要修改此变量

    public _1_14_ComparableGenericArray() {
        this.array = (T[])(new Comparable[DEFAULT_SIZE]);
    }

    public _1_14_ComparableGenericArray(int size) {
        this.array = (T[])(new Comparable[size]);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        array = (T[])(new Comparable[DEFAULT_SIZE]);
        size = 0;
    }

    public void insert(T x, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) { // 需要动态扩容
            enlargeArray();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = x;
        size++;
    }

    public void insert(T x) { // 在末尾添加
        insert(x, size);
    }

    public boolean remove(T x) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == x) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                flag = true;
                size--;
                break;
            }
        }
        return flag;
    }

    public T findMax() {
        if (array == null || array.length == 0) {
            return null;
        }
        T max = array[0];
        for (T x : array) {
            if (x.compareTo(max) > 0) {
                max = x;
            }
        }
        return max;
    }

    public T findMin() {
        if (array == null || array.length == 0) {
            return null;
        }
        T min = array[0];
        for (T x : array) {
            if (x.compareTo(min) < 0) {
                min = x;
            }
        }
        return min;
    }

    private void enlargeArray() {
        T[] newArr = (T[])(new Comparable[2 * array.length + 1]);
        for (int i = 0; i < size; i++) {
            newArr[i] = array[i];
        }
        array = newArr;
    }

    public void print() {
        for (T x : array) {
            System.out.print(x + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _1_14_ComparableGenericArray<Integer> comparableGenericArray = new _1_14_ComparableGenericArray<>(4);
        for (int i = 1; i < 10; i++) {
            comparableGenericArray.insert(i);
        }
        comparableGenericArray.print();
    }
}

