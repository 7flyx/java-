package binaryTree;

public class demo4 {
    public static void main(String[] args) {
        //给定一个头结点，判断该树是不是搜索二叉树
        // 二叉搜索树，没有重复的值
        //1. 判断左子树是不是BST，以及拿到左子树的最大值
        //2. 判断右子树是不是BST，以及拿到右子树的最小值
        //3. 判断这个结点的值是不是大于左子树的最大值，  小于右子树的最小值


    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class info {
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int max;
        public int min;

        public info(boolean isAllBST, int maxSubBSTSize, int max, int min) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean findNodeOfBST(Node head) {
        if (head == null) {
            return false;
        }

        return process(head).isAllBST;
    }

    public static info process(Node head) {
        //递归结束条件
        if (head == null) {
            return null;
        }

        //拿到左右子树的信息
        info leftInfo = process(head.left);
        info rightInfo = process(head.right);

        //整合信息，判断当前结点往下，是不是搜索二叉树
        int max = head.val;
        int min = head.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        int maxSubBSTSize = 0; //这个结点的最大二叉搜索树的结点
        if (leftInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
        }
        if (rightInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }

        //判断这个结点往下，是不是搜索二叉树
        boolean isAllBST = false;
        /*
        if ((leftInfo == null? true: leftInfo.isAllBST) &&
                (rightInfo == null? true: rightInfo.isAllBST) &&
                (leftInfo == null? true: (leftInfo.max < head.val)) &&
                (rightInfo ==  null? true : (rightInfo.min > head.val))) {
            isAllBST = true;
            maxSubBSTSize = (leftInfo == null? 0:leftInfo.maxSubBSTSize) +
                    (rightInfo == null? 0: rightInfo.maxSubBSTSize) + 1;

        }
        */
        if ((leftInfo == null || leftInfo.isAllBST) &&
                (rightInfo == null || rightInfo.isAllBST) &&
                (leftInfo == null || (leftInfo.max < head.val)) &&
                (rightInfo == null || (rightInfo.min > head.val))) {

            isAllBST = true;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }

        return new info(isAllBST, maxSubBSTSize, max, min);
    }
}
