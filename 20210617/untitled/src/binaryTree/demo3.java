package binaryTree;

public class demo3 {
    public static void main(String[] args) {
        //给定一个二叉树的的头结点，判断是不是平衡二叉树  ---分析如下
        //1. 判断左子树是不是平衡的
        //2. 判断右子树是不是平衡的
        //3. 分别得到左右子树的高度，判断两边的高度差，来得到这个根结点的平衡


    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node (int val) {
            this.val = val;
            right = left = null;
        }
    }

    //通过这样一个类来实现结构体的功能，反映一个结点左右子树的情况
    public static class info {
        boolean isAllAVL; //是不是平衡二叉树
        public int height; //最大高度

        public info(boolean isAllAVL, int height) {
            this.isAllAVL = isAllAVL;
            this.height = height;
        }
    }

    public static boolean isAVLOfNode(Node head) {
        if (head == null) {
            return true;
        }

        return process(head).isAllAVL;
    }

    public static info process(Node head) {
        //递归结束标志
        if (head == null) {
            return new info(true,0);
        }

        //分别拿到左右子树的信息
        info leftInfo = process(head.left);
        info rightInfo = process(head.right);

        boolean isAllAVL = false;
        if (leftInfo.isAllAVL && rightInfo.isAllAVL &&
                Math.abs(leftInfo.height - rightInfo.height) < 2) {
            isAllAVL = true;
        }
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        return new info(isAllAVL,height);
    }



}
