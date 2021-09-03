package demo;

import java.util.*;

/**
 * Created by flyx
 * Description: 迪杰斯特拉算法
 * User: 听风
 * Date: 2021-09-03
 * Time: 14:20
 */
public class Dijistra {

    public static class EdgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public HashMap<Node, Integer> dijistra(Node from) {
        if (from == null) {
            return null;
        }
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> lockSet = new HashSet<>();
        distanceMap.put(from, 0);
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeCompare());
        Node minNode = getNoLockAndMinDistance(distanceMap, lockSet); //返回没有用过，并且路径最小的节点
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) { //distance表中没有，就新建
                    distanceMap.put(toNode, distance + edge.weight);
                }
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight)); //原本节点的路径与新的路径，取最小值
            }
            lockSet.add(minNode);
            minNode = getNoLockAndMinDistance(distanceMap, lockSet);
        }
        return distanceMap;
    }

    public Node getNoLockAndMinDistance(HashMap<Node, Integer> distanceMap, HashSet<Node> lockSet) {
        Node res = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry: distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!lockSet.contains(node) && distance < minDistance) {
                res = node;
                minDistance = distance;
            }
        }
        return res;
    }
}
