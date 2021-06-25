package demo2;

public class demo2 {
    public static void main(String[] args) {
        //图的相关算法
        //常见的图的存储方式就是邻接表和邻接矩阵，还有就是边集数组的形式，
        //所以通常我们将写一个接口，转换为统一的方式进行存储，


    }

    public static Graph arrayToGraph(int[][] array) { //边集数组传入进来
        if (array == null) {
            return null;
        }
        Graph graph = new Graph();
        for (int i = 0; i < array.length; i++) {
            int weight = array[i][0]; //权值
            int from = array[i][1]; //起点
            int to = array[i][2]; //终点

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            //拿到数值对应的Node结点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            //调整这两个结点的属性
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
