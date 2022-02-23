import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 19:50
 * Description:
 */
public class LeetCode34_TreePathSum {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static List<List<Integer>> list;

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        list = new ArrayList<>();
        process(root, target, new ArrayList<>(), 0);
        return list;
    }

    private static void process(TreeNode node, int target, ArrayList<Integer> res, int sum) {
        if (node.left == null && node.right == null) {
            if (sum + node.val == target) {
                List<Integer> tmp = new ArrayList<>();
                for (int num : res) {
                    tmp.add(num);
                }
                tmp.add(node.val);
                list.add(tmp);
            }
            return;
        }

        res.add(node.val);
        if (node.left != null) {
            process(node.left, target, res, sum + node.val);
        }
        if (node.right != null) {
            process(node.right, target, res, sum + node.val);
        }
        res.remove(res.size() - 1);
    }

    public static void main(String[] args) {
//        TreeNode node = new TreeNode(5);
//        node.left = new TreeNode(4);
//        node.right = new TreeNode(8);
//        node.left.left = new TreeNode(11);
//        node.left.left.left = new TreeNode(7);
//        node.left.left.right = new TreeNode(2);
//        node.right.left= new TreeNode(13);
//        node.right.right= new TreeNode(4);
//        node.right.right.left= new TreeNode(5);
//        node.right.right.right= new TreeNode(1);
//        List<List<Integer>> res = pathSum(node, 22);

        int eor = 4;
        System.out.println(eor & -eor);
        System.out.println(eor & (~eor + 1));
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<int[]> list2 = new ArrayList<>();
        list.toArray(new int[2][]);


    }
}
