import java.util.Arrays;
import java.util.Stack;

class Node {
    public int val;
    public Node left, right;

    public Node(int val) {
        this.val = val;
    }
}

public class Demo {
    public static void main(String[] args) {
//        Node node = new Node(1);
//        node.left = new Node(2);
//        node.right = new Node(3);
//        node.left.left = new Node(4);
//        node.left.right = new Node(5);
//        node.right.left = new Node(6);
//        node.right.right = new Node(7);
//
//        postOrderTraversalNor(node);



        TestHeap heap = new TestHeap();
        int[] arr = {1,5,2,6,9,8,4,3};
        heap.createBigHeap(arr);
        System.out.println(Arrays.toString(heap.elem));

        heap.push(10);
        System.out.println(Arrays.toString(heap.elem));
    }


    public static void postOrderTraversalNor(Node root){
        if(root == null) return;
        Node prev = null;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == prev) {
                stack.pop();
                System.out.print(cur.val + " ");
                prev = cur;
                cur = null;// 这个y被打印了  不能再次入栈
            } else {
                cur = cur.right;
            }
        }
    }

}


