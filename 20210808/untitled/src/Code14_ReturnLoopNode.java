/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 22:26
 */
public class Code14_ReturnLoopNode {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head; //都从第一个结点出发
        while (fast != null && fast.next != null) { //如果不是循环链表，就退出
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { //相等了
                fast = head; //从头开始
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
