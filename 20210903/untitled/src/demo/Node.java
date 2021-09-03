package demo;
import java.util.*;

public class Node {
	public int val;
	public int in;
	public int out;
	public ArrayList<Node> nexts; //ֱ���¼��ڵ�
	public ArrayList<Edge> edges; //ֱ���¼���
	
	public Node(int val) {
		this.val = val;
		this.in = 0;
		this.out = 0;
		nexts = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
}
