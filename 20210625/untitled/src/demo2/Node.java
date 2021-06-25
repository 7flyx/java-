package demo2;

import java.util.ArrayList;

public class Node {
    //点集类
    public int value;  //结点的编号
    public int in; //入度
    public int out; //出度
    public ArrayList<Node> nexts; //出度的点
    public ArrayList<Edge> edges; //出度的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }
}
