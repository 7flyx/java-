/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-02
 * Time: 10:20
 * Description: 反转单链表LeetCode206
 */
public class Code12_ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
