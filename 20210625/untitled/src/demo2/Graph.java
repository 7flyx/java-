package demo2;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    //将点和边，集合起来组成图
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }
}
