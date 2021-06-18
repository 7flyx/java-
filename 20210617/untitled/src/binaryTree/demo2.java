package binaryTree;

public class demo2 {
    public static void main(String[] args) {

    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
            left = right = parent = null;
        }
    }

    public static Node findNextNode(Node node) {
        //寻找这个结点的下一结点
        //分为两种情况，有右子树和  没有右子树的情况
        if (node == null) {
            return null;
        }
        if (node.right != null) {//有右子树的情况
            return getLeftMost(node.right);
        } else { //没有右子树的情况
            //既然没有左子树，就需要向上遍历，去循环该节点为左子树的结点时，即可
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = parent.parent; //向上走
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node node) {
        //直接循环找到这个子树的左子树
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public static void printCrease(int n) {
        //传入一个参数n，计算一张纸条经过n次对折，从上到下打印每一个折痕，分为凹凸折痕
        //分析得到，这个n，其实就是一个二叉树的深度，且该二叉树的左子树根结点为凹，右子树根结点全为凸，且整颗树的根结点为凹
        print(1,n,true); //第一个参数为第几层，第二个为整棵树的深度，第三个参数表示凹凸，true为凹，false为凸
    }

    private static void print(int i, int n, boolean sign) {
        if (i > n) {
            return;
        }
        //中序遍历即可
        print(i+1,n,true); //左子树为凹
        String s = sign? "凹": "凸";
        System.out.print(s + " ");
        print(i+1,n,false); //右子树为凸
    }
}
