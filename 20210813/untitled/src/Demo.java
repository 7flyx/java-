import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by flyx
 * Description: 非递归遍历、层序遍历以及求得整棵树最宽的结点数
 * User: 听风
 * Date: 2021-08-13
 * Time: 16:50
 */
public class Demo {
    public static void main(String[] args) {

        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        inOrderUnRecur(head);
        System.out.println();

        posOrderRecur(head);
        System.out.println();
        posOrderUnRecur1(head);
        System.out.println();
        levelOrder(head);
        System.out.println();
        System.out.println(getLevelMaxNodeNum1(head) + " " + getLevelMaxNodeNum2(head));
    }

    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.val + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.val + " ");
    }

    public static void preOrderUnRecur(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.val + " ");
                //先压右再压左
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void posOrderUnRecur1(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> help = new Stack<>(); //辅助栈，stack弹出的结果放入这里
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                help.push(head);
                //先压左再压右
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }
            while (!help.isEmpty()) {
                System.out.print(help.pop().val + " ");
            }
        }
    }

    public static void posOrderUnRecur2(TreeNode head) {
        if (head != null) {
            //后序遍历，只用一个栈
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            TreeNode preNode = head; //上次打印过的结点，初始化为head
            TreeNode cur = null; //当前移动的结点
            while (!stack.isEmpty()) {
                cur = stack.peek(); //拿到栈顶元素，并未弹出栈顶元素
                if (cur.left != null && cur.left != preNode && cur.right != preNode) { //先看左子树访问过没有
                    stack.push(cur.left);
                } else if (cur.right != null && cur.right != preNode) { //再看右子树访问过没有
                    stack.push(cur.right);
                } else {
                    preNode = stack.pop();
                    System.out.print(preNode.val + " "); //上面两个都没中，说明就访问过了，弹出栈顶元素
                }
            }
        }
    }

    public static void inOrderUnRecur(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right; //向右转
                }
            }
        }
    }

    public static void levelOrder(TreeNode head) {
        //层序遍历
        if (head != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                System.out.print(head.val + " ");
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
            }
        }
    }

    public static int getLevelMaxNodeNum1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        //求出这个树最宽的结点数--哈希表实现
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        HashMap<TreeNode, Integer> levelMap = new HashMap<>(); //这张表得到每个结点所在的深度
        levelMap.put(head, 1);
        int res = -1;
        int curLevelNodeNum = 0; //当前这一层的结点数
        int curLevel = 1; //当前深度
        while (!queue.isEmpty()) {
            head = queue.poll();
            int curNodeLevel = levelMap.get(head); //得到这个结点的深度
            if (curLevel == curNodeLevel) { //当前结点还在同一层
                curLevelNodeNum++;
            } else {
                res = Math.max(res, curLevelNodeNum); //取最大值
                curLevel++;
                curLevelNodeNum = 1; //重置结点数
            }

            if (head.left != null) {
                levelMap.put(head.left, curNodeLevel + 1);
                queue.add(head.left);
            }
            if (head.right != null) {
                levelMap.put(head.right, curNodeLevel + 1);
                queue.add(head.right);
            }
        }
        return Math.max(res, curLevelNodeNum); //最后还是得判断一下最后一层的结点数
    }

    public static int getLevelMaxNodeNum2(TreeNode head) {
        if (head == null) {
            return 0;
        }

        //不用哈希表，直接有两个指针。一个是当前层最右结点的指针，另一个是下一层最右结点的指针
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode curEnd = head; //当前层最靠右的结点
        TreeNode nextEnd = null; //下一层最靠右的结点
        int res = -1;
        int curLevelNodeNum = 1;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }

            if (head == curEnd) { //最右的结点
                res = Math.max(res, curLevelNodeNum);
                curLevelNodeNum = 1;
                curEnd = nextEnd;
                nextEnd = null;
            } else {
                curLevelNodeNum++;
            }
        }
        return res;
    }
}
