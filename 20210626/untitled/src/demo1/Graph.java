package demo1;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    //点和边组合，就是图
    public HashMap<Integer, Node> nodes; //点，由它自己的value去对应NODE结点
    public HashSet<Edge> edges; //整个图，所有的边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
