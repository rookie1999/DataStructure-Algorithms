package cn.zhanguozhi.chapter1_Introduction;

public class _1_13_Collection {

    private Object[] collection;
    private int size = 0; // 数组中存在元素的实际个数
    private final int DEFAULT_SIZE = 3;

    _1_13_Collection() {
        collection = new Object[DEFAULT_SIZE];
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        doClear();
    }

    private void doClear() {
        collection = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void insert(Object element, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (size == collection.length) {
            this.expandArray();
        }
        for (int i = size; i > index; i--) {
            collection[i] = collection[i - 1];
        }
        collection[index] = element;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        for (int i = index; i < size - 1; i++) {
            collection[i] = collection[i + 1];
        }
        size--;
    }

    public boolean isPresent(Object x) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (collection[i].equals(x)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    private void expandArray() {
        Object[] newArr = new Object[2 * collection.length + 1];
        for (int i = 0; i < collection.length; i++) {
            newArr[i] = collection[i];
        }
        collection = newArr;
    }
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(collection[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _1_13_Collection collect = new _1_13_Collection();
        System.out.println("isEmpty:" + collect.isEmpty());
        for (int i = 1; i < 10; i++) {
            collect.insert(i, i-1);
        }
        collect.print();
        System.out.println("isEmpty:" + collect.isEmpty());
        System.out.println("isPresent:" + collect.isPresent(4));
        collect.remove(3);
        collect.remove(3);
        System.out.println("isPresent:" + collect.isPresent(4));
        collect.print();
        collect.makeEmpty();
        System.out.println("isEmpty:" + collect.isEmpty());
        collect.print();
    }
}
