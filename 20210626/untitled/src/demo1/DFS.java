package demo1;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    //深度优先遍历----需要记录路径
    public void dfs(Node node) {
        if (node != null) {
            Stack<Node> stack = new Stack<>(); //实则这个就是记录路径的
            HashSet<Node> set = new HashSet<>(); //还是为了防止死循环

            stack.push(node);
            set.add(node);
            System.out.print(node.value + " "); //压入就输出
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                for (Node next : tmp.nexts) {
                    if (!set.contains(next)) {
                        stack.push(tmp); // 记得先压入才弹出的结点，后面返回需要
                        stack.push(next);
                        set.add(next);
                        System.out.print(next.value + " ");
                        break;  //找到一个结点就退出，去找这个结点的下一个结点
                    }
                }
            }
        }
    }
}
