/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-04
 * Time: 8:52
 * Description: Morris遍历
 */
class Node {
    public int val;
    public Node left, right;

    public Node(int val) {
        this.val = val;
    }
}

public class Morris {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.print("前序遍历：");
        morrisPre(head);
        System.out.println();

        System.out.print("中序遍历：");
        morrisIn(head);
        System.out.println();

        System.out.print("后序遍历：");
        morrisPost(head);
        System.out.println();

    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }

        Node cur =  head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { //有左子树的情况
                //查找最靠右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //上面循环停下来，要么是该节点右孩子为null，要么就是为cur的情况
                if (mostRight.right == null) { //第一次来到这个最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个最右节点
                    mostRight.right = null;
                }
            }
            cur = cur.right; //没有左子树的情况，就转向右子树
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }

        Node cur =  head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { //有左子树的情况
                //查找最靠右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //上面循环停下来，要么是该节点右孩子为null，要么就是为cur的情况
                if (mostRight.right == null) { //第一次来到这个最右节点
                    System.out.print(cur.val + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个最右节点
                    mostRight.right = null;
                }
            } else { //没有左子树的情况
                System.out.print(cur.val + " ");
            }

            cur = cur.right; //没有左子树的情况，就转向右子树
        }

    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }

        Node cur =  head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { //有左子树的情况
                //查找最靠右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //上面循环停下来，要么是该节点右孩子为null，要么就是为cur的情况
                if (mostRight.right == null) { //第一次来到这个最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个最右节点
                    mostRight.right = null;
                    System.out.print(cur.val + " ");
                }
            } else {
                System.out.print(cur.val + " ");
            }

            cur = cur.right; //没有左子树的情况，就转向右子树
        }
    }

    public static void morrisPost(Node head) {
        if (head == null) {
            return;
        }

        Node cur =  head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) { //有左子树的情况
                //查找最靠右的节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //上面循环停下来，要么是该节点右孩子为null，要么就是为cur的情况
                if (mostRight.right == null) { //第一次来到这个最右节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次来到这个最右节点
                    mostRight.right = null;
                    printNode(cur.left);
                }
            }
            cur = cur.right; //没有左子树的情况，就转向右子树
        }
        printNode(head); //最后还得打印整棵树，最靠右的节点
    }

    private static void printNode(Node node) {
        if (node == null) {
            return;
        }

        //逆序node的右子树，像单链表一样
        Node cur = reverseList(node); //新的根结点
        node = cur; //保存新的根节点
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right; //向右子树走
        }
        reverseList(node);
    }

    private static Node reverseList(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.right; //右子树逆序
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
