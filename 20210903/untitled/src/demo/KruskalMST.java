package demo;
import java.util.*;


public class KruskalMST {
	//克鲁斯卡尔算法--最小生成树
	//以边为单位，将所有的边排升序，然后弹出边，进行判断。运用并查集
	
	public static class UnionSet {
		private HashMap<Node, Node> fatherMap;
		private HashMap<Node, Integer> sizeMap;
		
		public UnionSet() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}
		
		public void initSet(List<Node> list) {
			for (Node node : list) {
				fatherMap.put(node, node); //指向自己
				sizeMap.put(node, 1);
			}
		}
		
		public boolean isSameSet(Node node1, Node node2) {
			if (node1 == null || node2 == null) {
				return false;
			}
			return getFather(node1) == getFather(node2);
		}
		
		public Node getFather(Node node) {
			Stack<Node> stack = new Stack<Node>();
			while (node != fatherMap.get(node)) {
				stack.push(node);
				node = fatherMap.get(node);
			}
			//路径压缩
			while (!stack.isEmpty()) {
				fatherMap.put(stack.pop(), node);
			}
			return node;
		}
		
		public void union(Node node1, Node node2) {
			if (node1 == null || node2 == null) {
				return;
			}
			
			Node father1 = getFather(node1);
			Node father2 = getFather(node2);
			
			if (father1 != father2) {
				if (sizeMap.get(father1) > sizeMap.get(father2)) {
					fatherMap.put(father2,father1); //小挂大
					sizeMap.put(father1, sizeMap.get(father1) + sizeMap.get(father2));
					sizeMap.remove(father2);
				} else {
					fatherMap.put(father1,father2); //小挂大
					sizeMap.put(father2, sizeMap.get(father1) + sizeMap.get(father2));
					sizeMap.remove(father1);
				}
			}
		}
	}

	public static class EdgeCompare implements Comparator<Edge> {
		
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}
	
	public Set<Edge> kruskalMST(Graph graph) {
		if (graph == null) {
			return null;
		}
		
		Set<Edge> res = new HashSet<Edge>();
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new EdgeCompare());
		for (Edge edge : graph.edges) {
			queue.add(edge);
		}
		
		UnionSet findSet = new UnionSet();
		findSet.initSet(graph.nodes);
		
		while (!queue.isEmpty()) {
			Edge minEdge = queue.poll();
			Node toNode = minEdge.to;
			Node fromNode = minEdge.from;
			if (!findSet.isSameSet(toNode, fromNode)) {
				res.add(minEdge);
				findSet.union(fromNode, toNode);
			}
		}
		
		return res;
	}
	
}
