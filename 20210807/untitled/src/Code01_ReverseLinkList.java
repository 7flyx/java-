/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-07
 * Time: 10:19
 */
public class Code01_ReverseLinkList {

    //三指针法
    public ListNode reverseLinkList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode newHead = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            if (next == null) {
                newHead = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return newHead;
    }

    //头插法
    public ListNode reverseLinkList2(ListNode head) {
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
