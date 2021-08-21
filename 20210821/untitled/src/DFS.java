import java.util.HashSet;
import java.util.Stack;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-21
 * Time: 10:02
 */
public class DFS {
    public void dfs(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.print(node.value + " ");
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            for (Node next : tmp.outNode) {
                if (!set.contains(next)) {
                    System.out.print(next.value + " ");
                    set.add(next);
                    stack.push(tmp); //再次压入这个节点
                    stack.push(next);
                    break; //压入一个节点，就弹出
                }
            }
        }
    }
}
