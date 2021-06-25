package demo2;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public void dfs(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>(); //此时的栈，记录的其实是路径
        HashSet<Node> set = new HashSet<>(); //有环，防止死循环
        stack.push(node);
        set.add(node);
        System.out.print(node.value + " ");

        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            for (Node n : tmp.nexts) {
                if (!set.contains(n)) {
                    stack.push(tmp); //再次压入刚弹出的结点
                    stack.push(n); //压入没有遍历过的结点
                    set.add(n);
                    System.out.print(n.value + " "); //入栈时，先把数据输出了
                    break; //压入一个数据后，就直接弹出，去循环下一个结点的信息
                }
            }
        }
    }
}
