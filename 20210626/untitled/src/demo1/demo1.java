package demo1;

public class demo1 {
    public static void main(String[] args) {
        //测试类
        //在面对一些图的算法，我们只需要写一个接口，转换为我们自己熟悉的结构，再去写算法
        //算法题一般的图的存储类型就是  邻接表  邻接矩阵  边集数组


    }

    public static Graph arrayToGraph(int[][] array) {
        if (array == null) {
            return null;
        }

        //array[i][0] 权值     array[i][1] 起点    array[i][2] 终点
        Graph graph = new Graph();
        for (int i = 0; i < array.length; i++) {
            int weight = array[i][0];
            int from = array[i][1];
            int to = array[i][2];

            //包装为NODE结点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            //取出包装好的NODE结点，修改结点相应的属性
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
