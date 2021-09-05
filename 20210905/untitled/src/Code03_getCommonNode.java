/**
 * Created by flyx
 * Description: 给定两个链表，返回两个链表的公共祖先。若没有，则返回null
 * User: 听风
 * Date: 2021-09-05
 * Time: 9:31
 */
public class Code03_getCommonNode {

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node cur = head1;
        for (int i = 0; i < 10; i++) {
            cur.next = new Node(i + 2);
            cur = cur.next;
        }
        Node head2 = new Node(20);
        cur = head2;
        for (int i = 0; i < 5; i++) {
            cur.next = new Node(i  + 13);
            cur = cur.next;
        }
        Node node = head1;
        for (int i = 0; i < 6; i++) {
            node = node.next;
        }
        cur.next = node; //连接

        System.out.println(getCommonNode1(head1, head2).val);
        System.out.println(getCommonNode2(head1, head2).val);
    }

    //解法一：长链表走差值步
    public static Node getCommonNode1(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //计算两个链表的长度，让长链表先走差值步
        int k = 0;
        Node node1 = head1;
        Node node2 = head2;
        while(node1 != null) {
            k++;
            node1 = node1.next;
        }
        while (node2 != null) {
            k--;
            node2 = node2.next;
        }

        node1 = k > 0? head1 : head2; //长链表给node1
        node2 = node1 == head1? head2 : head1; //短链表给node2

        k = Math.abs(k);
        while (k -- > 0) {//长链表先走差值步
            node1 = node1.next;
        }

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    //解法二：整体看成循环链表，两个链表互相连接，总路程就一样。速度一样，定能相遇
    public static Node getCommonNode2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node node1 = head1;
        Node node2 = head2;
        while (node1 != node2) {
            node1 = node1 == null? head2 : node1.next;
            node2 = node2 == null? head1 : node2.next;
        }
        return node1;
    }
}
