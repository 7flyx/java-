package BinarySearchTree;

import java.util.Stack;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-20
 * Time: 17:11
 */

class TreeNode {
    int val; //关键字
    TreeNode left; //左子节点
    TreeNode right; //右子节点

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BST {
    private TreeNode root; //根结点

    public void insert(int val) { //插入新的节点
        //root = process(val, root);

        TreeNode node = new TreeNode(val); //先创建好节点
        TreeNode parent = null; //父节点，用于连接新的节点
        TreeNode cur = root; //当前移动的节点

        if (root == null) {
            root = node; //还没有根结点的情况
        } else {
            while (true) {
                parent = cur;
                if (val < cur.val) { //小于当前节点的情况
                    cur = cur.left;
                    if (cur == null) { //如果为null了，说明走到了最后的节点
                        parent.left = node;
                        return;
                    }
                } else { //大于当前节点的情况
                    cur = cur.right;
                    if (cur == null) {
                        parent.right = node; //如果为null，就走到最后节点了
                        return;
                    }
                }
            }
        }
    }

    private TreeNode process(int val, TreeNode node) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) { //小于当前节点
            node.left = process(val, node.left);
        } else {
            node.right = process(val, node.right); //大于等于当前节点
        }
        return node;
    }

    public boolean remove(int val) { //删除对应的节点
        if (root == null) {
            throw new RuntimeException("root is null.");
        }

        TreeNode parent = root;
        TreeNode cur = root;
        boolean isLeftChild = true;

        while (cur != null && cur.val != val) { //循环查找需要被删除的节点
            parent = cur;
            if (val < cur.val) {
                cur = cur.left;
                isLeftChild = true;
            } else {
                cur = cur.right;
                isLeftChild = false;
            }
        }

        if (cur == null) { //没找到需要删除的节点
            return false;
        }

        //找到了需要被删除的节点
        if ( cur.left== null && cur.right == null) { //叶节点的情况
            if (cur == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (cur.right == null) {
            if (cur == root) {
                root = root.left;
            } else if (isLeftChild) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        } else if (cur.left == null) { //只有一个孩子节点的情况
            if (cur == root) {
                root = root.right;
            } else if (isLeftChild) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        } else { //有两个孩子节点的情况
            TreeNode minNode = findMinNode(cur.right);
            if (cur == root) {
                root = minNode;
            } else if (isLeftChild) {
                parent.left = minNode;
            } else {
                parent.right = minNode;
            }
            minNode.left = cur.left; //新节点minNode的左孩子指向被删除节点cur的左孩子
            // C/C++语言，需要回收cur内存空间
        }
        return true;
    }

    private TreeNode findMinNode(TreeNode head)  {
        if (head == null) {
            return null;
        }

        TreeNode pre = null;
        TreeNode cur = head;
        TreeNode next = head.left;
        while (next != null) {
            pre = cur;
            cur = next;
            next = next.left; //一直寻找该树的最左的节点
        }
        if (pre != null) {
            pre.left = cur.right; //cur就是最左边的节点，pre的cur的父节点。父节点的left指向cur的right
            cur.right = head; //cur的right指向head这个根结点
        }
        return cur; //返回最左边的节点
    }

    public void remove2(int val) {
        if (root == null) {
            throw new RuntimeException("root is null.");
        }

        process2(val, root);
    }

    private TreeNode process2(int val, TreeNode node) {
        if (node == null) {
            return null;
        }
        if (val < node.val) {
            node.left = process2(val, node.left);
        } else if (val > node.val){
            node.right = process2(val, node.right);
        } else if (node.left != null && node.right != null) { //上面的if没成立，说明val相等。这里是两个孩子节点的情况
            node.val = getMinNodeVal(node.right); //覆盖右子树中最小的节点值
            node.right = process2(node.val, node.right); // 重新对已经覆盖的数值进行删除
        } else { //只有一个孩子节点或者没有节点的情况
            node = node.left != null? node.left : node.right;
        }
        return node;
    }

    private int getMinNodeVal(TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = node;
        while (cur != null) {
            pre = cur;
            cur = cur.left;
        }
        return pre.val;
    }

    public boolean contains(int val) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            } else if (val < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    public void display() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }
}
