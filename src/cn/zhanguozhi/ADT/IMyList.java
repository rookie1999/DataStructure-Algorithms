package cn.zhanguozhi.ADT;

import java.util.Iterator;

public interface IMyList<AnyType> extends Iterable<AnyType> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(AnyType x);
    void addObject(AnyType x);
    void add(int idx, AnyType x);
    boolean removeObject(AnyType x);
    void remove(int idx);
    AnyType get(int idx);
    AnyType set(int idx, AnyType newVal);

    @Override
    Iterator<AnyType> iterator();
}
