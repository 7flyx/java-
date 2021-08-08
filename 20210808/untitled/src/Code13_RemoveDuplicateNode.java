/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 22:25
 */
public class Code13_RemoveDuplicateNode {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next; //首先保存cur的下一结点，在这个结点上去做比较
            pre = cur;
            while (next != null) { //从next往下走，每一个结点都与cur比较
                if (cur.val == next.val) {
                    pre.next = next.next; //相等的情况，pre直接连接下一个结点
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
