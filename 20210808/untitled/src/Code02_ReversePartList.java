import java.io.*;
/**
 * Created by flyx
 * Description: 反转部分单链表
 * User: 听风
 * Date: 2021-08-07
 * Time: 20:21
 */

public class Code02_ReversePartList {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        String[] values = sc.readLine().split(" ");
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values[i]));
            cur = cur.next;
        }
        head = head.next;

        String[] nums = sc.readLine().split(" ");
        head = reversePartList(head, Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reversePartList(ListNode head, int L, int R) {
        if (head == null || head.next == null || L > R) { //没有结点，或者只有一个结点的情况
            return head;
        }

        ListNode cur = head;
        ListNode pre = null; //临时变量

        ListNode left = null; //表头的上一个结点
        ListNode right = null; //尾结点的下一个结点

        ListNode start = null; //需要反转链表的表头
        ListNode end = null; //需要反转链表的尾结点
        for (int i = 1; i <= R && cur != null; i++) {
            if (i == L) {
                left = pre; //pre 是 cur的前驱结点
                start = cur;
            }
            if (i == R) {
                end = cur;
                right = cur.next; //反转链表的尾结点  的下一结点
            }
            pre = cur;
            cur = cur.next;
        }

        reverse(left, start, end, right);
        return left == null? end : head; //有可能反转后，头结点被换了。
    }

    public static void reverse(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode next = null;
        ListNode pre = right; //头结点需要连接right，比如上图  2号结点连接5号结点
        while (start != right) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        //循环结束后，此时pre指向反转链表的尾结点，也就是上图的 4号结点
        if (left != null) {
            left.next = pre; //上图的  1号结点连接4号结点
        }
    }
}

