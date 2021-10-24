
/**
 * Created with IntelliJ IDEA.
 * User: 12629
 * Date: 2021/10/24
 * Time: 11:29
 * Description:
 */
public class BinarySearchTree {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root;

    public TreeNode search(int key) {
        return search(this.root, key);
    }

    private TreeNode search(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.val) {
            return search(node.left, key);
        } else if (key > node.val) {
            return search(node.right, key);
        }
        return node; //等于的情况
    }

    public boolean insertTree(int val) {
        if(root == null) {
            root = new TreeNode(val);
            return true;
        }
        root = insertTree(this.root, val);
        return true;
    }

    private TreeNode insertTree(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        } else if (val < node.val) {
            node.left = insertTree(node.left, val);
        } else if (val > node.val){
            node.right = insertTree(node.right, val);
        }
        return node;
    }

}
