package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class demo1 {
    public static void main(String[] args) {
        //二叉树的序列化与反序列化
        //就是将一个二叉树的数据放入一个数组；将数组的内容又重新建成一颗二叉树

    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
            left = right = null;
        }
    }

    //无论是前序中序还是后序遍历方式，都是这样的写法，只是需要稍微修改一下递归与入队的顺序
    //前序遍历方式，进行序列化
    public static Queue<String> presArray(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }
    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null); //自动转换为字符串
        } else {
            ans.add(String.valueOf(head.val));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    //前序遍历方式，进行反序列化
    public static Node buildOfQueue (Queue<String> ans) {
        if (ans == null || ans.size() == 0) {
            return null;
        }
        return preb(ans);
    }
    public static Node preb(Queue<String> ans) {
        String val = ans.poll();
        if (val == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(val));
        head.left = preb(ans);
        head.right = preb(ans);
        return head;
    }


    //层序遍历的方式，序列与反序列化，都需要使用两个队列进行，一个作为返回，一个作为辅助
    public static Queue<String> levelSerial(Node head) {
        //层序遍历的方式，进行序列化

        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node tmp = queue.poll();
                if (tmp.left != null) {
                    queue.add(tmp.left);
                    ans.add(String.valueOf(tmp.left.val));
                } else {
                    ans.add(null);
                }

                if (tmp.right != null) {
                    queue.add(tmp.right);
                    ans.add(String.valueOf(tmp.right.val));
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node BuildOflevelSerial(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }

        Node head = createNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = createNode(levelList.poll());
            node.right = createNode(levelList.poll());

            if (node.left != null) { //左孩子不为空，入队列
                queue.add(node.left);
            }
            if (node.right != null) { //右孩子不为空，入队列
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node createNode(String val) {
        if (val == null) {
            return null;
        } else {
            return new Node(Integer.valueOf(val));
        }
    }

}
