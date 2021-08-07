/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-07
 * Time: 11:36
 */
public class Code04_MergeLinkList {
    public ListNode mergeTwoLists(ListNode headA, ListNode headB) {
        if(headA == null) return headB;
        if(headB == null) return headA;

        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;

        while(headA != null && headB != null) {
            if(headA.val < headB.val) {
                tmp.next = headA;
                headA = headA.next;
            } else {
                tmp.next = headB;
                headB = headB.next;
            }
            tmp = tmp.next;
        }

        if (headA != null) {
            tmp.next = headA;
        } else {
            tmp.next = headB;
        }
        return newHead.next;
    }
}
