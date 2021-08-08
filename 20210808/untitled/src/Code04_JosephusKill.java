/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 22:38
 */
public class Code04_JosephusKill {
    public ListNode josephusKill(ListNode head, int k) {
        if (head == null || head.next == head) { //没有结点，或者只有一个结点的情况
            return head;
        }

        int count = 1; //计数
        ListNode pre = null;
        ListNode cur = head;
        while (cur != cur.next) { //当自己的next指向自己时，说明只有一个结点了
            if (count++ == k) {
                pre.next = cur.next;
                count = 1; //重置为1
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return cur;
    }
}
