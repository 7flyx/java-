/**
 * Created by flyx
 * Description: //返回中下结点或者中间结点
 * User: 听风
 * Date: 2021-08-07
 * Time: 10:16
 */
public class Code02_MidLastListNode {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
