package demo1;

import java.util.ArrayList;

public class Node {
    //点集
    public int value; //编号，也是这个结点的身份
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in =0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
