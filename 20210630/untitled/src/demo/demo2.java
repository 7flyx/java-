package demo;

import java.util.Stack;

public class demo2 {
    public static void main(String[] args) {
        //给你一个栈，不需要使用额外的数据结构，如何逆序整个栈的数据。允许使用有限的几个变量
        //递归函数

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reserveStack(stack);

        for (Integer i : stack) {
            System.out.println(i);
        }

    }

    public static int f(Stack<Integer> stack) {
        //釜底抽薪，将栈底的元素返回。
        if (stack.size() == 1) {
            return stack.pop(); //弹出最后一个元素
        } else {
            int cur = stack.pop(); //先弹出栈顶元素，然后递归到下一个
            int last = f(stack);
            stack.push(cur); //压入弹出的元素
            return last; //一直向上返回栈顶的元素
        }
    }

    public static void reserveStack(Stack<Integer> stack) {
        if (stack != null && stack.size() != 0) {
            int cur = f(stack); //抽出栈底的元素
            reserveStack(stack); //递归抽出当前栈的栈底元素
            stack.push(cur); //当栈被抽空时，这个递归也就结束了，只需原路返回，重新压入弹出的数据
        }
    }
}
