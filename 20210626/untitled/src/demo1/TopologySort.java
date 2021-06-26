package demo1;

import java.util.*;

public class TopologySort {
    //拓扑排序---将所有入度为0的点取出，相应的点的入度需要修改
    //也就是  必须完成前面一件事，才能开始执行后面的事
    public List<Node> topologySort(Graph graph) {
        //传递进来的一定是有向无环图
        //先将所有入度为0的点，加入HashMap
        List<Node> result = new ArrayList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>(); //入度为0的点，进入队列，等会遍历使用
        for (Node next : graph.nodes.values()) {
            inMap.put(next, next.in);
            if (next.in == 0) {
                zeroInQueue.add(next);
            }
        }

        //宽度遍历
        while (!zeroInQueue.isEmpty()) {
            Node tmp = zeroInQueue.poll();
            result.add(tmp);
            for (Node next : tmp.nexts) {
                inMap.put(next, inMap.get(next) - 1); //入度减1
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
