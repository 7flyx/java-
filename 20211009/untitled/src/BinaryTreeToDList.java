/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-09
 * Time: 22:21
 * Description: 搜索二叉树转双链表
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeToDList {
    private TreeNode curMaxNode, newHead; //指向当前链表的最大值。然后进行中序遍历

    public TreeNode Convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        //return process(root).front;
        inOrderConnect(root);
        return newHead;
    }

    private static class Info {
        public TreeNode front, rear;

        public Info(TreeNode front, TreeNode rear) {
            this.front = front;
            this.rear = rear;
        }
    }

    //方法一
    private Info process(TreeNode node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) {
            return new Info(node, node);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        TreeNode front = node;
        TreeNode rear = node;
        if (left != null) {
            front = left.front;
            node.left = left.rear;
            left.rear.right = node;
        }
        if (right != null) {
            rear = right.rear;
            node.right = right.front;
            right.front.left = node;
        }
        return new Info(front, rear);
    }

    //方法二
    private void inOrderConnect(TreeNode node) {
        if (node.left != null) {
            inOrderConnect(node.left);
        }

        node.left = this.curMaxNode;
        if (this.curMaxNode != null) {
            curMaxNode.right = node;
        } else {
            this.newHead = node;
        }
        this.curMaxNode = node;

        if (node.right != null) {
            inOrderConnect(node.right);
        }
    }
}
