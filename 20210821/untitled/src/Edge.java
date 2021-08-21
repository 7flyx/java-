/**
 * Created by flyx
 * Description: 图的边
 * User: 听风
 * Date: 2021-08-21
 * Time: 9:42
 */
public class Edge {
    public int val; //权重
    public Node from; //起点
    public Node to; //终点

    public Edge(int val, Node from, Node to) {
        this.to = to;
        this.from = from;
        this.val = val;
    }
}
