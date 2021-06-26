package demo1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijkstra {
    /*
        最短路径---迪杰特斯拉算法
        前提是： 给的图的边必须是正数
        通过图中的一个结点出发，自己到自己的权值为0，接下来就是在剩余的点（与自己这个点相通的）取最小的点进行循环操作

     */
    public HashMap<Node, Integer> dijkstra1(Node from) {
        //计算from点到各点的最短路径
        if (from == null) {
            return null;
        }

        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> lockNode = new HashSet<>(); //对于已经确定好的值，我们需要记录，不再需要对其操作了

        Node minNode = getMinNodeAndNoLock(distanceMap, lockNode); //第一次，肯定是拿到from本身
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) { //没有遍历过，即就是正无穷的权值
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode,
                            Math.min(distance + edge.weight, distanceMap.get(toNode))); //判断这个结点原先的权值与新路径的权值
                }
            }

            lockNode.add(minNode);
            minNode = getMinNodeAndNoLock(distanceMap, lockNode);
        }
        return distanceMap;
    }

    public Node getMinNodeAndNoLock(HashMap<Node, Integer> distanceMap, HashSet<Node> lockNode) {
        //distanceMap中没有的点，说明还没遍历到，此时就返回MAX
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey(); //拿到键
            int distance = distanceMap.get(node);
            if (!lockNode.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
}
