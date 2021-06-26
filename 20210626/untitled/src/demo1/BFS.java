package demo1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    //宽度优先遍历
    public void bfs(Node node) {
        if (node != null) {
            Queue<Node> queue = new LinkedList<>();
            HashSet<Node> set = new HashSet<>(); //图有环，防止死循环
            queue.add(node);
            set.add(node);
            while (!queue.isEmpty()) {
                Node tmp = queue.poll();
                System.out.print(tmp.value + " ");
                for (Node next : tmp.nexts) {
                    if (!set.contains(next)) {
                        set.add(next);
                        queue.add(next);
                    }
                }
            }
        }
    }
}
