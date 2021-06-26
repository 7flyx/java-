package demo1;

import java.util.ArrayList;

public class Node {
    //点集结构
    public int value; //结点里面的数据，也是编号
    public int in; //入度
    public int out; //出度
    public ArrayList<Node> nexts; //出度的所有点
    public ArrayList<Edge> edges; //出度的所有边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
