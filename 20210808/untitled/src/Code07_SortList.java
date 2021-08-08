import java.io.*;
/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 15:37
 */

public class Code07_SortList {
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

        head = sortList(head);
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
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        ListNode tail = null;

        while (head != null) {
            ListNode minNode = null;
            ListNode minPre = null;
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                if (minNode == null || cur.val < minNode.val) {
                    minPre = pre; //保存最小结点的前驱结点
                    minNode = cur; //保存最小结点
                }
                pre = cur;
                cur = cur.next;
            }

            if (minNode == head) {
                head = head.next; //换头结点的情况
            } else {
                minPre.next = minNode.next; //将最小结点拿出
            }

            if (newHead == null) {
                newHead = minNode;
                tail = minNode;
            } else {
                tail.next = minNode;
                tail = minNode; //尾插法
            }
        }
        tail.next = null;
        return newHead;
    }
}
