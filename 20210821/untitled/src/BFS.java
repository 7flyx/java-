import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by flyx
 * Description: 宽度优先遍历
 * User: 听风
 * Date: 2021-08-21
 * Time: 9:52
 */
public class BFS {

    public void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>(); //已经遍历过的点
        queue.add(node); //将第一个节点加入队列
        set.add(node);

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.print(tmp.value + " ");
            for (Node next : tmp.outNode) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
