import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by flyx
 * Description:  输入参数格式  [1,2,3,4,5]    2
 *                  给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * User: 听风
 * Date: 2021-08-08
 * Time: 16:28
 */




class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) { //k=1时，等于没有反转
            return head;
        }

        ListNode left = null;
        ListNode start = head;
        ListNode cur = head;
        int count = 1;
        while (cur != null) {
            ListNode next = cur.next;
            if (count++ == k) { //反转
                reverse(left, start, cur, cur.next); //这里的end和right，就是cur和cur.next
                if (start == head) { //更换头结点
                    head = cur;
                }
                left = start; //更新left和start的指向
                start = start.next; //right
                count = 1;
            }
            cur = next;
        }
        return head;
    }

    public void reverse(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode pre = right;
        ListNode next = null;
        while (start != right) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        if (left != null) {
            left.next = pre; //也就是上图的1号结点  连接4号结点
        }
    }
}

public class Code08_ReversekNodeList {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            ListNode ret = new Solution().reverseKGroup(head, k);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}