package demo;
import java.util.Stack;
import java.util.HashSet;

public class DFS {
	public void dfs(Node node) {
		if (node == null) {
			return;
		}
		
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		set.add(node);
		stack.push(node);
		System.out.println(node.val);
		while (!stack.isEmpty()) {
			node = stack.pop();
			for (Node next : node.nexts) {
				if (!set.contains(next)) {
					stack.push(node); //����ѹ��ڵ�
					stack.push(next);//ѹ���µĽڵ�
					set.add(next);
					System.out.println(next.val);
					break;
				}
			}
		}
	}
}
