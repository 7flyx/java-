package demo1;

public class LinklListDemo {
    public static void main(String[] args) {
        //链表的相关面试题练习
        /*
            近似于返回中点值，快慢指针一般都能用
            快慢指针的运用 --- 根据第一次跳转fast来判断，slow 与fast的初始化，画图即可解
            1. 输入链表头结点，奇数长度返回中点，偶数长度返回上中点
            2. 输入链表头结点，奇数长度返回中点，偶数长度返回下中点
            3. 输入链表头结点，奇数长度返回中点前一点，偶数长度返回上中点前一个
            4. 输入链表头结点，奇数长度返回中点前一点，偶数长度返回下中点前一个
         */
        Node n = null;
        n = new Node();
        n.val = 10;
        n.next = new Node();
        n.next.val = 20;
        n.next.next = new Node();
        n.next.next.val = 30;
        Node tail = n.next.next;
        tail.next = new Node();
        tail = tail.next;
        tail.val = 40;
        tail.next = new Node();
        tail = tail.next;
        tail.val = 50;

        //Node slow = midOrPrior(n);
        //Node slow = midOrRear(n);
        //Node slow = priorOfMidOrPrior(n);
        Node slow = priorOfMidOrPriorOfMid(n);
        System.out.println(slow.val);
    }

    public static class Node {
        public int val;
        public Node next;

        public Node() {
            val = 0;
            next = null;
        }
    }

    public static Node midOrPrior(Node head) {
        //奇数长度返回中点，偶数长度返回上中点
        if (head == null || head.next == null || head.next.next == null) {
            return head; //最多只有两个结点时，直接返回
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) { //判断下两节点的情况
            slow = slow.next; //一步
            fast = fast.next.next; //两步
        }
        return slow;
    }

    public static Node midOrRear(Node head) {
        //奇数返回中点，偶数返回下中点
        if (head == null || head.next == null) {
            return head; //最多只有一个结点时，直接返回
        }

        Node slow = head.next;
        Node fast = head.next; //都同时指向第二个结点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node priorOfMidOrPrior(Node head) {
        //奇数返回中点前一个，偶数返回上中点前一个
        if (head == null || head.next == null || head.next.next == null) {
            return head; //最多有两个结点，直接返回
        }

        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node priorOfMidOrPriorOfMid(Node head) {
        //奇数返回中点前一个，偶数返回下中点前一个
        if (head == null || head.next == null) {
            return head; //只有一个结点，直接返回
        }

        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
