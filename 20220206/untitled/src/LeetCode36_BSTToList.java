import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 15:04
 * Description:LeetCode剑指offer36 二叉树转双链表
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

public class LeetCode36_BSTToList {

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node head = null;
        Node cur = null;
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (head == null) {
                    head = cur = root;
                } else {
                    cur.right = root;
                    root.left = cur;
                    cur = root;
                }
                root = root.right;
            }
        }
        head.left = cur;
        cur.right = head; //头尾相连，循环链表
        return head;
    }

    public static Node treeToDoublyList1(Node root) {
        if (root == null) {
            return null;
        }
        return process(root).start;
    }

    private static class Info {
        public Node start, end;

        public Info(Node left, Node right) {
            this.start = left;
            this.end = right;
        }
    }

    private static Info process(Node root) {
        if (root == null) {
            return new Info(null, null);
        }
        Info L = process(root.left);
        Info R = process(root.right);
        if (L.end != null) {
            L.end.right = root;
        }
        if (R.start != null) {
            R.start.left = root;
        }
        root.left = L.end;
        root.right = R.start;
        return new Info(L.start != null? L.start : root,
                R.end != null? R.end : root);
    }

    public static void main(String[] args) {
        Node node = new Node(4);
        node.left = new Node(2);
        node.right = new Node(5);
        node.left.left = new Node(1);
        node.left.right = new Node(3);
        Node node1 = treeToDoublyList(node);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.right;
        }

    }
}
