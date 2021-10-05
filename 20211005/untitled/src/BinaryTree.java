import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-05
 * Time: 14:24
 * Description: 二叉树的非递归遍历
 */
public class BinaryTree {
    private static class Node {
        public int val;
        public Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        preOrderNoRecur(head); //前序
        System.out.println();

        inOrderNoRecur(head); //中序
        System.out.println();

        postOrderNoRecur(head); //后序
        System.out.println();

        postOrderNoRecur2(head); //后序
        System.out.println();

        System.out.println(getHeightOfBinaryTree(head)); //高度
        System.out.println(getSumOfTreeNode(head)); //节点数总和
        System.out.println(getSumOfLeafNode(head)); //叶子节点数
        System.out.println(getSumOfKLevel(head, 4)); //k层的节点数
        System.out.println(findNumberOfTree(head, 6).val); //查找节点
    }

    /**
     * 非递归前序遍历
     *
     * @param node 根结点
     */
    public static void preOrderNoRecur(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) { //先压右
                stack.push(node.right);
            }
            if (node.left != null) { //再压左
                stack.push(node.left);
            }
        }
    }

    /**
     * 非递归中序遍历
     *
     * @param node 根结点
     */
    public static void inOrderNoRecur(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    /**
     * 非递归后序遍历-两个栈实现
     *
     * @param node 根结点
     */
    public static void postOrderNoRecur(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> help = new Stack<>(); //辅助栈
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            help.push(node);
            if (node.left != null) { //先压左
                stack.push(node.left);
            }
            if (node.right != null) { //再压右
                stack.push(node.right);
            }
        }

        while (!help.isEmpty()) {
            System.out.print(help.pop().val + " ");
        }

    }

    /**
     * 非递归后序遍历-一个栈
     *
     * @param node 根结点
     */
    public static void postOrderNoRecur2(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Node prev = node; //上一次打印输出的节点
        while (!stack.isEmpty()) {
            node = stack.peek();
            if (node.left != null && node.left != prev && node.right != prev) { //左边不为null，并且两个孩子都没打印输出过
                stack.push(node.left);
            } else if (node.right != null && node.right != prev) { //右边不为null，并且右边没打印输出过
                stack.push(node.right);
            } else { //左右两个孩子都打印输出过，就输出当前节点的值
                node = stack.pop();
                prev = node;
                System.out.print(node.val + " ");
            }
        }

    }

    /**
     * 计算二叉树的高度
     *
     * @param node 根结点
     * @return 高度
     */
    public static int getHeightOfBinaryTree(Node node) {
        if (node == null) {
            return 0;
        }

        int left = getHeightOfBinaryTree(node.left);
        int right = getHeightOfBinaryTree(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 计算二叉树的节点数
     *
     * @param node 根结点
     * @return 节点数
     */
    public static int getSumOfTreeNode(Node node) {
        if (node == null) {
            return 0;
        }

        int left = getSumOfTreeNode(node.left);
        int right = getSumOfTreeNode(node.right);
        return left + right + 1;
    }

    /**
     * 求叶子节点数
     *
     * @param node 根结点
     * @return 节点数
     */
    public static int getSumOfLeafNode(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = getSumOfLeafNode(node.left);
        int right = getSumOfLeafNode(node.right);
        return left + right;
    }

    /**
     *  查找k层的节点数
     * @param node 根结点
     * @param k 层数
     * @return 节点数
     */
    public static int getSumOfKLevel(Node node, int k) {
        if (node == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        Node nextLevelRightNode = null; //下一层最靠右的节点
        Node curLevelRightNode = node; //当前层最靠右的节点
        int curLevel = 1; //当前层数
        int sum = 1;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                nextLevelRightNode = node.left;
                queue.add(node.left);
            }
            if (node.right != null) {
                nextLevelRightNode = node.right;
                queue.add(node.right);
            }

            if (node != curLevelRightNode) { //不等于当前层最右的节点
                sum++;
            } else {
                if (curLevel == k || queue.isEmpty()) { //到达预定层，提前弹出
                    break;
                }
                sum = 1;
                curLevel++;
                curLevelRightNode = nextLevelRightNode;
                nextLevelRightNode = null;
            }

        }
        return curLevel == k? sum : -1; //判断层数是否相等
    }

    public static Node findNumberOfTree(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        }

        Node left = findNumberOfTree(node.left, val);
        Node right = findNumberOfTree(node.right, val);
        return left != null? left : right; //谁不是null，就返回谁
    }
}
