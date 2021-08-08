import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/**
 * Created by flyx
 * Description: 给出一个单链表，返回删除单链表的倒数第 K 个节点的链表。
 * User: 听风
 * Date: 2021-08-07
 * Time: 22:15
 */

public class Code03_RemoveBackKNode {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = sc.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        int k = Integer.parseInt(nums[1]);
        String[] values = sc.readLine().split(" ");
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values[i]));
            cur = cur.next;
        }
        head = head.next;

        //删除第倒数k个结点
        head = delBackKNode(head, k);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public static ListNode delBackKNode(ListNode head, int k) {
        if (head == null || k < 1) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null; //slow的前驱结点
        for (; fast != null; fast = fast.next) {
            if (--k < 0) {
                pre = slow; //pre紧跟着slow
                slow = slow.next;
            }
        }
        pre.next = slow.next; //C++的朋友，需要自己手动回收ListNode结点的内存
        return head;
    }

}