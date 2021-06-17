package demo1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class demo {
    public static void main(String[] args) {
        //二叉树的前序中序后序遍历，非递归算法

    }

    public static class Node {
        public int val;
        public Node right;
        public Node left;

        public Node(int val) {
            this.val = val;
            right = left = null;
        }
    }

    //三种遍历方式---栈实现
    public static void pre(Node head) {
        //前序遍历
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");

                //先压入右边再是左边，这样弹出来的才是头左右的顺序
                if (head.right != null) {
                    stack.push(head.right); //先压入右子树
                }
                if (head.left != null) {
                    stack.push(head.left);  //后压入左子树
                }
            }
        }
    }

    public static void postorder1(Node head) {
        if (head != null) {
            //后序遍历，将前序遍历的代码稍微修改一下即可
            //头右左，最后从尾部开始输出即可
            Stack<Node> stack1 = new Stack<>(); //主要栈
            Stack<Node> stack2 = new Stack<>(); //辅助栈
            stack1.push(head);

            while (!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.push(head);

                //先左子树再右子树，弹出就是右子树左子树的顺序
                if (head.left != null) {
                    stack1.push(head.left); //先压入左子树
                }
                if (head.right != null) {
                    stack1.push(head.right); //后压入右子树
                }
            }

            //最后输出辅助栈的数据即可
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().val + " ");
            }
            System.out.println();
        }
    }

    public static void postorder2(Node head) {
        if (head != null) {
            //使用一个栈，实现后序遍历
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node h = head;  //指向上一次被打印的值，初始值为head
            Node c = null; //随时在移动的指针
            while (!stack.isEmpty()) {
                c = stack.peek(); //拿到当前栈顶元素，并不删除栈顶元素
                if (c.left != null && c.left != h && c.right != h) { //左右孩子都没被访问过，h也代表子树是否被访问过
                    stack.push(c.left); //先压入左边
                } else if (c.right != null && c.right != h) { //从上面下来，说明左子树已经被访问，还有可能右子树没被访问
                    stack.push(c.right);
                } else { //上面两个都没进去，说明左右子树都已经访问过了, 直接输出当前结点的值即可，最后更新h的指向
                    System.out.print(stack.pop().val + " ");
                    h = c;
                }
            }
        }
    }

    public static void in(Node head) {
        if (head != null) {
            //中序遍历----左中右的顺序
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
    }


    //层序遍历---队列实现
    public static int getMaxWidth1(Node head) {
        //获取这颗树，那一层最宽的结点数---
        //map实现和队列实现
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>(); //以队列的形式实现
        queue.add(head);

        HashMap<Node, Integer> levelMap = new HashMap<>(); //键存储结点，值存储这个结点在第几层
        levelMap.put(head, 1); //头结点，在第一层
        int curLevel = 1; //当前你正在统计的第几层
        int curNodeWidth = 0; //当前层的宽度
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll(); //弹出头部元素
            int curNodeLevel = levelMap.get(cur); //得到这个结点在第几层
            if (cur.left != null) {
                queue.add(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1); //既然是左孩子，肯定在当前结点的下一层
            }
            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1); //右孩子，肯定在当前结点的下一层
            }

            if (curNodeLevel == curLevel) {
                curNodeWidth++; //如果现在统计的层数与curLevel相等，说明在同一层，即相应的值+1
            } else {
                //说明不在同一层了，需要走到下一层，重新更新结点数等等的数据
                max = Math.max(max, curNodeWidth);
                curLevel++;
                curNodeWidth = 1; //因为是进入了下一层，即就是已经来到了下一层的结点，这里初始值为1
            }
        }
        max = Math.max(max, curNodeWidth);  //上面的else语句，不一定就是最大值已经进去了，需重新补一下最大值的更新
        return max;
    }

    public static int getMaxWidth2(Node head) {
        //队列实现----层序遍历的方式，入队
        //确定每一层的最后结点的位置
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; //当前这一层最右的结点是谁
        Node nextEnd = null; //下一层最右的结点是谁
        int curLevelNodes = 0; //当前这一层的结点数
        int max = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }

            curLevelNodes++; //弹出一个结点后，再计数，而不是在入队列的时候计数的。始终计算的是当前这一层的结点数
            if (cur == curEnd) { //来到了这一层的最后一个结点处
                max = Math.max(max,curLevelNodes);
                curEnd = nextEnd;
                curLevelNodes = 0;  //注意区分这个方法，与上面的用HashMap实现的区别
            }
        }
        return max;
    }

}
