package demo2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>(); //有环，防止死循环
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) { //队列循环
            Node tmp = queue.poll();
            System.out.print(tmp.value);

            for (Node n : tmp.nexts) {
                if (!set.contains(n)) {
                    queue.add(n);
                    set.add(n);
                }
            }
        }
    }
}
