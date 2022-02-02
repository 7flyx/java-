/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-02
 * Time: 9:19
 * Description: 排序单链表LeetCode148
 */
public class Code08_SortLinkList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //归并排序
        ListNode fast = head.next;
        ListNode slow = head;
        //找上中节点的位置，不然容易进行栈溢出
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null; //将中间节点断开
        ListNode left = sortList(head);
        ListNode right = sortList(fast);
        //合并两个有序的单链表
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        //始终有一个单链表不是空的
        cur.next = right != null? right : left;
        return res.next;
    }
}
