package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.StackImplByArray;

public class StackImplByArrayTest {

    public static void main(String[] args) {
        StackImplByArray<Integer> stack = new StackImplByArray<>();
        System.out.println("size:" + stack.size());
        System.out.println("isEmpty:" + stack.isEmpty());
        for (int i = 1; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        stack.printStack();
        System.out.println("size:" + stack.size());
        System.out.println("isEmpty:" + stack.isEmpty());
        stack.clear();
        System.out.println("size:" + stack.size());
        System.out.println("isEmpty:" + stack.isEmpty());
        for (int i = 1; i < 20; i++) {
            stack.push(i);
        }
        stack.printStack();

    }
}
