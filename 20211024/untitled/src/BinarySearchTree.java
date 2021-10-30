
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

    public void remove(int key) {
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null) {
            if(cur.val < key) {
                parent = cur;
                cur = cur.right;
            }else if(cur.val == key) {
                removeNode(parent,cur);
                return;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }
    }

    private void removeNode(TreeNode parent,TreeNode cur) {
        if(cur.left == null) {
            if(cur == root) {
                root = cur.right;
            }else if(cur == parent.left) {
                parent.left = cur.right;
            }else {
                parent.right = cur.right;
            }
        }else if(cur.right == null) {
            if(cur == root) {
                root = cur.left;
            }else if(cur == parent.left) {
                parent.left = cur.left;
            }else {
                parent.right = cur.left;
            }
        }else {
            TreeNode pre = null;
            TreeNode next = cur.right; //往右子树，找最小值
            while (next.left != null) {
                pre = next;
                next = next.left;
            }

            if (pre != null) {
                pre.left = next.right;
            }
        }
    }

    public void print(TreeNode node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.print(node.val + " ");
        print(node.right);
    }

}
