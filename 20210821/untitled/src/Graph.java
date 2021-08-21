import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-21
 * Time: 9:49
 */
public class Graph {
    public HashMap<Integer, Node> nodes; //由编号，得到对应的node点
    public ArrayList<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }
}
