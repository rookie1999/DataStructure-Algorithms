package cn.zhanguozhi.ADT;

import java.util.Iterator;

/**
 * @author zhanguozhi
 * @date 2019.12.3
 * @desc 手工实现的链表类必须要实现的接口
 */
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
