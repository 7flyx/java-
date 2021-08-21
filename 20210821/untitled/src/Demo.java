/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-21
 * Time: 10:09
 */
public class Demo {
    public static void main(String[] args) {

    }

    public static Graph arrayToGraph(int[][] array) { //边集数组转换为Graph
        if (array == null) {
            return null;
        }

        //起点  终点    权重
        Graph graph = new Graph();
        int n = array.length; //行
        for (int i = 0; i < n; i++) {
            int from = array[i][0];
            int to = array[i][1];
            int weight = array[i][2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            //拿到数值所对应的节点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);

            //修改节点相应的属性
            fromNode.out++;
            toNode.in++;
            fromNode.outNode.add(toNode);
            fromNode.outEdge.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }


}
