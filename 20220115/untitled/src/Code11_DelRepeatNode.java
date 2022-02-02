/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-02
 * Time: 10:08
 * Description: 删除链表中的重复节点
 */
public class Code11_DelRepeatNode {
    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null; //记录前驱节点
        ListNode cur = head;
        ListNode next = cur.next;
        boolean flag = false; //表示暂时还没有重复值
        while (next != null) {
            if (cur.val == next.val) {
                flag = true;
                next = next.next;
                cur.next = next;
            } else {
                if (!flag) {
                    pre = cur;
                    cur = next;
                } else if (pre != null) {
                    pre.next = next;
                    cur = next;
                } else {
                    head = next;
                    cur = next;
                }
                next = next.next;
                flag = false;
            }
        }
        if (flag) { //最后一个节点的情况
            if (pre != null) {
                pre.next = null;
            } else {
                head = null;
            }
        }
        return head;
    }
}
