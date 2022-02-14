import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-14
 * Time: 19:55
 * Description:剑指offer54 搜索二叉树的第k大节点
 */
public class LeetCode54_MaxKNodeOfBST {

    class solution {
        public int kthLargest(TreeNode root, int k) {
            if (root == null || k < 1) {
                return -1;
            }

            Info ans = new Info(-1, 0);
            process(root, k, ans);
            return ans.res;
        }

        private class Info {
            public int res;
            public int index;

            public Info(int res, int index) {
                this.res = res;
                this.index = index;
            }
        }

        private void process(TreeNode node, int k, Info ans) {
            if (node == null) {
                return;
            }
            process(node.right, k, ans); //先向右子树
            //结算结果
            ans.index++;
            if (k == ans.index) {
                ans.res = node.val;
                return; //提前结束递归函数
            }
            process(node.left, k, ans); // 再向左子树
        }
    }

    //根据Morris改，使空间复杂度为O(1)
    public int morris(TreeNode node, int k) {
        if (node == null || k < 1) {
            return -1;
        }

        TreeNode mostRight = null;
        int res = -1;
        while (node != null) {
            mostRight = node.right;
            if (mostRight != null) {
                while (mostRight.left != null && mostRight.left != node) {
                    mostRight = mostRight.left;
                }
                if (mostRight.left == null) { //第一次来到这个节点
                    mostRight.left = node;
                    node = node.right;
                    continue;
                } else { //第二次来到该节点
                    mostRight.left = null;
                }
            }

            //中序遍历的操作
            if (--k == 0) {
                res = node.val;
            }
            node = node.left;
        }

        Stack<Integer> stack = new Stack<>();
        stack.size();

        return res;
    }

}
