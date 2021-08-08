import java.io.*;
/**
 * Created by flyx
 * Description:  CD160 按照左右半区的方式重新组合单链表
 * User: 听风
 * Date: 2021-08-08
 * Time: 21:12
 */


public class Code12_AgainCombinationList {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        String[] values1 = sc.readLine().split(" ");
        ListNode head1 = new ListNode(0);
        ListNode cur = head1;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values1[i]));
            cur = cur.next;
        }
        head1 = head1.next;
        againCombinationList(head1);
        while (head1 != null) {
            System.out.print(head1.val + " ");
            head1 = head1.next;
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

    public static ListNode againCombinationList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //当循环停下的时候，slow的下一结点就是整个链表的中间结点
        ListNode right = slow.next; //右半部分的链表
        slow.next = null; //置为null
        ListNode left = head; //左半部分的链表
        while (left.next != null) {
            ListNode next = right.next;
            right.next = left.next;
            left.next = right;

            left = right.next;
            right = next;
        }
        left.next = right;
        return head;
    }
}
