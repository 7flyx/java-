package demo1;

import java.util.*;

public class PrimMST {
    //最小生成树---普利姆算法
    //以点为中心展开，循环当前已经解锁的点的最小边

    public static class EdgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public Set<Edge> primMST(Graph graph) {
        if (graph == null) {
            return null;
        }

        HashSet<Node> nodeSet = new HashSet<>(); //已经使用过的点
        HashSet<Edge> edgeSet = new HashSet<>(); //已经使用过的边
        PriorityQueue<Edge> edgePrQueue = new PriorityQueue<>(new EdgeCompare());
        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) { //循环作用： 有可能给的图是一个“森林”
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    if (!edgeSet.contains(edge)) { //不加重复的边
                        edgeSet.add(edge);
                        edgePrQueue.add(edge);
                    }
                }

                while (!edgePrQueue.isEmpty()) {
                    Edge minEdge = edgePrQueue.poll(); //弹出此时最小的边
                    Node toNode = minEdge.to; //终点
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);
                        result.add(minEdge);

                        for (Edge nextEdge : toNode.edges) {
                            if (!edgeSet.contains(nextEdge)) {
                                edgeSet.add(nextEdge);
                                edgePrQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
