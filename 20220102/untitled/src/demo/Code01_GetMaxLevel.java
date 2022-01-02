package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-02
 * Time: 12:44
 * Description:二叉树的最大深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class Code01_GetMaxLevel {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return morris(root);
    }

    private int morris(TreeNode root) {
        TreeNode cur = root;
        //TreeNode pre = root; //上次遍历过的节点
        TreeNode mostRight = null;
        int curLevel = 0;
        int res = Integer.MIN_VALUE; //系统最小值

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int len = 1; //记录长度
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                    len++;
                }

                if (mostRight.right == null) { //第一次来到这个节点
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个节点
                    if (mostRight.left == null) { //说明是叶子节点，结算
                        res = Math.max(curLevel, res);
                    }
                    mostRight.right = null;
                    curLevel -= len; //减去高度
                    //pre = cur;
                }
            } else { //只有右孩子，没有左孩子的情况。有可能也是叶子节点
                curLevel++;
            }
            cur = cur.right; //向右子树
        }

        //单独计算整棵树的最靠右的节点
        int finalRight = 1;
        while (root.right != null) {
            finalRight++;
            root = root.right;
        }
        if (root.left == null) {//最右的节点，是叶子节点，结算
            res = Math.max(res, finalRight);
        }

        return res;
    }

}
