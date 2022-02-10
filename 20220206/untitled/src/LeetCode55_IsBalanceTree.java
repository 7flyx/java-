/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-10
 * Time: 18:10
 * Description:LeetCode 剑指offer55，判断是不是平衡树
 */
public class LeetCode55_IsBalanceTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root).isBalance;
    }


    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        if (!left.isBalance || !right.isBalance) {
            left.isBalance = false;
            return left;
        }

        if (Math.abs(left.height - right.height) >= 2) {
            left.isBalance = false;
            return left;
        }
        left.height = Math.max(left.height, right.height) + 1;
        return left;
    }

    private static class Info {
        public boolean isBalance;
        public int height;
        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}
