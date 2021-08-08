import java.io.*;
/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 17:58
 */

public class Code10_MergeNodeList {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine()); //单链表1
        String[] values1 = sc.readLine().split(" ");
        ListNode head1 = new ListNode(0);
        ListNode cur = head1;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values1[i]));
            cur = cur.next;
        }
        head1 = head1.next;

        int m = Integer.parseInt(sc.readLine()); //单链表2
        String[] values2 = sc.readLine().split(" ");
        ListNode head2 = new ListNode(0);
        cur = head2;
        for (int i = 0; i < m; i++) {
            cur.next = new ListNode(Integer.parseInt(values2[i]));
            cur = cur.next;
        }
        head2 = head2.next;

        cur = mergeNodeList(head1, head2);
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }

        sc.close();
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode mergeNodeList(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return headA == null? headB : headA; //其中一个是null，返回另一个链表
        }

        ListNode newHead = null;
        ListNode tail = null; //尾指针，用尾插法
        while (headA != null && headB != null) { //两个链表都不是null，循环就继续
            if (headA.val < headB.val) {
                if (newHead == null) {
                    newHead = headA;
                } else {
                    tail.next = headA;
                }
                tail = headA;
                headA = headA.next; //继续往下走
            } else {
                if (newHead == null) {
                    newHead = headB;
                } else {
                    tail.next = headB;
                }
                tail = headB;
                headB = headB.next; //继续往下走
            }
        }

        //循环结束后，一定是有一个链表遍历完了，此时将另一个链表连接上即可
        tail.next = headA != null? headA : headB;
        return newHead;
    }
}
