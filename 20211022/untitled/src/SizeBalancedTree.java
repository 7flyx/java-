import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-22
 * Time: 16:28
 * Description: 以节点数来维持平衡的二叉树
 */

public class SizeBalancedTree {
    private static class SBTNode {
        public int val;
        public SBTNode left, right;
        public int size;

        public SBTNode(int val, int size) {
            this.val = val;
            this.size = size;
        }
    }

    private SBTNode root; //根节点

    private SBTNode L_Rotate(SBTNode node) {
        SBTNode right = node.right;
        node.right = right.left;
        right.left = node;

        right.size = node.size;
        node.size = (node.left != null? node.left.size : 0) +
                (node.right != null? node.right.size : 0) + 1;
        return right;
    }

    private SBTNode R_Rotate(SBTNode node) {
        SBTNode left = node.left;
        node.left = left.right;
        left.right = node;

        left.size = node.size; //继承原来的大小值
        //重新计算node节点的值
        node.size = (node.left != null? node.left.size : 0) +
                (node.right != null? node.right.size : 0) + 1;
        return left;
    }

    private SBTNode maintain(SBTNode cur) {
        if (cur == null) {
            return null;
        }

        int leftSize = cur.left != null ? cur.left.size : 0;
        int rightSize = cur.right != null ? cur.right.size : 0;
        int leftLeftSize = cur.left != null ? (cur.left.left != null ? cur.left.left.size : 0) : 0;
        int leftRightSize = cur.left != null ? (cur.left.right != null ? cur.left.right.size : 0) : 0;
        int rightLeftSize = cur.right != null ? (cur.right.left != null ? cur.right.left.size : 0) : 0;
        int rightRightSize = cur.right != null ? (cur.right.right != null ? cur.right.right.size : 0) : 0;

        if (leftLeftSize > rightSize) { //LL型
            cur = R_Rotate(cur);//右旋
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) { //LR型
            cur.left = L_Rotate(cur.left);
            cur = R_Rotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) { //RL型
            cur.right = R_Rotate(cur.right);
            cur = L_Rotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) { //RR型
            cur = L_Rotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        }
        return cur;
    }

    private SBTNode add(SBTNode cur, int val) {
        if (cur == null) {
            return new SBTNode(val, 1);
        } else if (cur.val > val) {
            cur.size++;
            cur.left = add(cur.left, val);
        } else {
            cur.size++;
            cur.right = add(cur.right, val);
        }

        return maintain(cur); //调整平衡树
    }

    public void add(int val) {
        if (root == null) {
            root = new SBTNode(val, 1);
        } else {
            root = add(root, val);
        }
    }

    public boolean contains(int val) {
        SBTNode cur = this.root;
        boolean response = false;
        while (cur != null) {
            if (cur.val > val) {
                cur = cur.left;
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                response = true;
                break;
            }
        }
        return response;
    }

    private SBTNode delete(SBTNode cur, int val) {
        cur.size--;
        if (cur.val > val) {
            cur.left = delete(cur.left, val);
        } else if (cur.val < val) {
            cur.right = delete(cur.right, val);
        } else {
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else {
                SBTNode pre = null;
                SBTNode des = cur.right; //向右子树查找最左节点
                des.size--;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size--; //最终的des节点，会重新计算节点数
                }
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right; //将des节点，替换cur节点
                }
                des.left = cur.left;
                des.size = des.left.size + (des.right != null ? des.right.size : 0) + 1;
                cur = des;
            }
        }
        //cur = maintain(cur); //平衡调整
        return cur;
    }

    public void delete(int val) {
        if (contains(val)) {
            root = delete(root, val);
        }
    }

    public int size() {
        return this.root == null? 0 : this.root.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void printInOrder() {
        SBTNode cur = this.root;
        Stack<SBTNode> stack = new Stack<>();
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
