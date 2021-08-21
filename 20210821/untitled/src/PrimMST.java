import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by flyx
 * Description:  普利姆算法，以点为单位，向外扩展
 * User: 听风
 * Date: 2021-08-21
 * Time: 15:31
 */
public class PrimMST {

    public Set<Edge> primMST(Graph graph) {
        if (graph == null) {
            return null;
        }

        HashSet<Edge> finishEdge = new HashSet<>(); //已经使用过的边
        HashSet<Node> finishNode = new HashSet<>(); //已经使用过的点
        PriorityQueue<Edge> priQueue = new PriorityQueue<>(new KruskalMST.EdgeCompare());
        Set<Edge> res = new HashSet<>();

        for (Node node: graph.nodes.values()) { //防止给的图是森林
            if (!finishNode.contains(node)) {
                finishNode.add(node);
                for (Edge edge: node.outEdge) {
                    if (!finishEdge.contains(edge)) {
                        finishEdge.add(edge);
                        priQueue.add(edge);
                    }
                }

                while (!priQueue.isEmpty()) {
                    Edge minEdge = priQueue.poll(); //弹出最小的边
                    if (!finishNode.contains(minEdge.to)) { //判断这个终点是否已经使用过
                        finishNode.add(minEdge.to);
                        res.add(minEdge);

                        for (Edge nextEdge: minEdge.to.outEdge) {
                            if (!finishEdge.contains(nextEdge)) {
                                finishEdge.add(nextEdge);
                                priQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

}
