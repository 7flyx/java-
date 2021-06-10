package demo1;

public class linkListCycleDemo {
    public static void main(String[] args) {
        //两个链表，可能有环也可能无环，请声明一个函数，返回相加的第一个结点，反之则放回null
        //三道面试题的合集
        //1. 给你一个链表，返回第一个入环的结点
        //2. 给你两个无环的链表，返回两个链表的相交结点
        //3/ 给你两个有环的链表，返回两个链表的相交的结点

    }

    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //先判断两个链表是否存在环---返回第一次入环的交点
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);

        //判断两个loop1与loop2的情况 只有同时有环或同时无环是，才有可能有相交结点
        if (loop1 == null && loop2 == null) {
            return noLoopNode(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return hasLoopNode(head1, loop1, head2, loop2);
        }

        return null; //一个有环，一个无环，必然没有相交结点
    }

    public static Node hasLoopNode(Node head1, Node loop1, Node head2, Node loop2) {
        //两个有环的话，就需要分为三种情况，进行讨论
        //1. 两个环并不相交，返回null即可
        //2. 两个环的交点，在环的外面
        //3. 两个环的交点，在环上

        if (loop1 == loop2) { //两个交点相等，说明是在环外相交的
            while (head1 != head2) {
                head1 = head1 == loop1 ? head2 : head1.next;
                head2 = head2 == loop2 ? head1 : head2.next;
            }
            return head1;
        } else {
            //两个交点不相等，说明在环上相遇，又或者根本不相交
            Node cur = loop1.next;
            while (cur != loop1) {

                if (cur == loop2) {
                    return loop2; //返回loop1或者loop2都可以
                }
                cur = cur.next;
            }
            return null;
        }
    }

    public static Node noLoopNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        while (head1 != head2) {
            head1 = head1 == null ? head2 : head1.next;
            head2 = head2 == null ? head1 : head2.next;
        }
        return head1;
    }

    public static Node getLoop(Node head) {
        //找到第一个相交的结点（环），如果没有的话，就返回null
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {  //判断是否有环的
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //循环出来，肯定是有环的，此时拿一个指针，从head开始走
        slow = head;
        while (slow != fast) { //两个指针同时走一步，一定会遇上
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
