import java.io.*;
import java.util.Stack;

/**
 * Created by flyx
 * Description:  给定一个链表，请判断该链表是否为回文结构。
 * User: 听风
 * Date: 2021-08-08
 * Time: 10:25
 */


public class Code05_IsPlalindromeList {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine()); //链表的长度
        String[] values = sc.readLine().split(" ");
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values[i]));
            cur = cur.next;
        }
        head = head.next;
        System.out.println(isPlalindromeList(head));
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    //栈
     public static boolean isPlalindromeList(ListNode head) {
         if (head == null || head.next == null) {
             return true;
         }
         Stack<Integer> stack = new Stack<>(); //栈
         ListNode cur = head;
         while (cur != null) {
             stack.push(cur.val); //压栈
             cur = cur.next;
         }

         cur = head;
         while (cur != null) {
             if (cur.val != stack.pop()) {
                 return false; //弹出的元素不相等的情况
             }
             cur = cur.next;
         }
         return true;
     }

    //进阶解法，将右边部分，反转链表，指向中间结点
    public static boolean isPlalindromeList3(ListNode head, int size) {
        if (head == null || head.next == null) {
            return true;
        }

        boolean res = true;
        ListNode leftStart = head;
        ListNode rightStart = null;
        ListNode cur = head;
        for (int i = 0; i < size / 2; i++) {
            cur = cur.next;
        }

        rightStart =  reverseList(cur); //右半部分的头结点
        cur = rightStart;
        for (int i = 0; i < size / 2; i++) {
            if (cur.val != leftStart.val) {
                res = false;
                break;
            }
            cur = cur.next;
            leftStart = leftStart.next;
        }
        reverseList(rightStart); //恢复链表，不需要接收返回值。本身上一个结点的next域，没被修改
        return res;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
