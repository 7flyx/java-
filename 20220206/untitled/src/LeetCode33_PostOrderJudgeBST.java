import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-14
 * Time: 19:41
 * Description:剑指offer33 根据后序数组判断是不是搜索二叉树
 */
public class LeetCode33_PostOrderJudgeBST {
    class Solution {
        public boolean verifyPostorder1(int[] postorder) {
            if (postorder == null) {
                return false;
            }
            return process(postorder, 0, postorder.length - 1);
        }

        private boolean process(int[] postorder, int L, int R) {
            if (L >= R) {
                return true;
            }
            int p = L; //从左开始（左子树，值最小）
            while (p < R && postorder[p] < postorder[R]) {
                p++; //遍历所有小于R节点的值
            }
            int mid = p; //此时的p就是右子树的开始
            while (p <= R && postorder[p] > postorder[R]) {
                p++;
            }
            return p == R && process(postorder, L, mid - 1) && process(postorder, mid, R - 1);
        }

        public boolean verifyPostorder(int[] postorder) {
            if (postorder == null) {
                return false;
            }

            //单调递减栈
            Stack<Integer> stack = new Stack<>();
            int root = Integer.MAX_VALUE;
            //从右向左遍历，根右左
            for (int i = postorder.length - 1; i >= 0; i--) {
                if (postorder[i] > root) {
                    return false;
                }

                while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                    root = stack.pop(); //已经遍历到较小的节点了，刷新根节点
                }
                stack.push(postorder[i]);
            }
            return true;
        }
    }
}
