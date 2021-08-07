/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-07
 * Time: 16:16
 */
public class demo {
    public static void main(String[] args) {

    }

    public static ListNode cycle1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
