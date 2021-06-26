package demo1;

import java.util.*;

public class KruskalMST {
    //最小生成树---克鲁斯卡尔算法
    //将整个图以最小的代价连通
    //以边为操作对象，使用并查集进行划分集合

    //并查集
    public static class UnionFind {
        private HashMap<Node, Node> parents; //前者结点本身，后者是前者的父亲结点
        private HashMap<Node, Integer> sizeMap; //代表点集合的总大小

        public UnionFind() {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) { //数据的初始化
            parents.clear();
            sizeMap.clear(); //先清理一下，避免上次使用未回收
            for (Node next : nodes) {
                parents.put(next, next);
                sizeMap.put(next, 1);
            }
        }

        public Node findFather (Node node) {
            if (node == null) {
                return null;
            }
            Stack<Node> path = new Stack<>();
            while (node != parents.get(node)) {
                path.push(node); //入栈，等会会做路径压缩
                node = parents.get(node); //指向父亲结点
            }
            while (!path.isEmpty()) {
                Node tmp = path.pop();
                parents.put(tmp, node); //指向node结点
            }
            return node;
        }

        public boolean isSameSet(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return false;
            }
            return findFather(node1) == findFather(node2);
        }

        public void union(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return;
            }

            Node head1 = findFather(node1);
            Node head2 = findFather(node2);
            if (head1 != head2) {
                int sizemap1 = sizeMap.get(head1);
                int sizemap2 = sizeMap.get(head2);
                if (sizemap1 > sizemap2) {
                    parents.put(head2, head1);
                    sizeMap.put(head1, sizemap1 + sizemap2);
                    sizeMap.remove(head2);
                } else {
                    parents.put(head1, head2);
                    sizeMap.put(head2, sizemap1 + sizemap2);
                    sizeMap.remove(head1);
                }
            }
        }
    }

    public static class EdgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight; //按边的权值进行排序
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        if (graph == null) {
            return null;
        }

        //并查集
        UnionFind unionfind = new UnionFind();
        unionfind.makeSets(graph.nodes.values()); //全部加入并查集进行初始化
        PriorityQueue<Edge> PrQueue = new PriorityQueue<>(new EdgeCompare());

        for (Edge edge : graph.edges) {
            PrQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!PrQueue.isEmpty()) {
            Edge minEdge = PrQueue.poll();
            if (!unionfind.isSameSet(minEdge.from, minEdge.to)) {
                unionfind.union(minEdge.from, minEdge.to);
                result.add(minEdge);
            }
        }
        return result;
    }
}
