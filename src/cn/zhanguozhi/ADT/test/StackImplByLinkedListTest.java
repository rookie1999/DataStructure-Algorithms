package cn.zhanguozhi.ADT.test;

import cn.zhanguozhi.ADT.StackImplByLinkedList;

public class StackImplByLinkedListTest {

    public static void main(String[] args) {
        StackImplByLinkedList<Integer> stack = new StackImplByLinkedList<>();
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
        stack.printStack();
        System.out.println("size:" + stack.size());
        System.out.println("isEmpty:" + stack.isEmpty());
        for (int i = 1; i < 20; i++) {
            stack.push(i);
        }
        stack.printStack();
    }
}
