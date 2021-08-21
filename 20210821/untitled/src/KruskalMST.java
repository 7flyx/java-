import java.util.*;

/**
 * Created by flyx
 * Description: 最小生成树-克鲁斯卡尔算法
 * User: 听风
 * Date: 2021-08-21
 * Time: 10:59
 */
public class KruskalMST {
    public static class Union {
        private final HashMap<Node, Node> parent; //指向父节点
        private final HashMap<Node, Integer> sizeMap; //大小

        public Union() {
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> list) {
            parent.clear();
            sizeMap.clear();

            for (Node node : list) {
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            if (node == null) {
                return null;
            }
            Stack<Node> path = new Stack<>(); //存储路径
            while (node != parent.get(node)) {
                path.push(node);
                node = parent.get(node);
            }
            while (!path.isEmpty()) {
                parent.put(path.pop(), node); //路径压缩
            }
            return node;
        }

        public boolean isSameSet(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return false;
            }

            return findFather(head1) == findFather(head2);
        }

        public void unionSet(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return;
            }

            Node father1 = findFather(head1);
            Node father2 = findFather(head2);
            if (father1 != father2) {
                if (sizeMap.get(father1) < sizeMap.get(father2)) {
                    parent.put(father1, father2);
                    sizeMap.put(father2, sizeMap.get(father1) + sizeMap.get(father2));
                    sizeMap.remove(father1);
                } else {
                    parent.put(father2, father1);
                    sizeMap.put(father1, sizeMap.get(father1) + sizeMap.get(father2));
                    sizeMap.remove(father2);
                }
            }
        }

    }

    public static class EdgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.val - o2.val; //左减右，升序
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        if (graph == null) {
            return null;
        }

        HashSet<Edge> res = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeCompare());
        Union mergeSearchList = new Union();
        mergeSearchList.makeSets(graph.nodes.values()); //将哈希表的值，转换为一个集合
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();
            if (!mergeSearchList.isSameSet(minEdge.from, minEdge.to)) {
                res.add(minEdge);
                mergeSearchList.unionSet(minEdge.from, minEdge.to);
            }
        }
        return res;
    }
}
