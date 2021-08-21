import java.util.ArrayList;

/**
 * Created by flyx
 * Description: 图的点
 * User: 听风
 * Date: 2021-08-21
 * Time: 9:39
 */
public class Node {
    public int value; //节点的编号
    public int in; //入度
    public int out; //出度
    public ArrayList<Node> outNode; //出度的点
    public ArrayList<Edge> outEdge; //出度的边

    public Node(int value) {
        this.value = value;
        outEdge = new ArrayList<>();
        outNode = new ArrayList<>();
    }
}
