/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-02
 * Time: 9:33
 * Description:奇偶单链表LeetCode328
 */
public class Code09_Odd_even_List {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode numberOne = new ListNode(0);
        ListNode numberTwo = new ListNode(0);
        ListNode node2 = head.next; //存储第二个节点，等会连接
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null || slow != null) {
            if (fast != null) {
                numberTwo.next = fast;
                numberTwo = fast;
                fast = fast.next != null? fast.next.next : null;
            }
            if (slow != null) {
                numberOne.next = slow;
                numberOne = slow;
                slow = slow.next != null? slow.next.next : null;
            }
        }
        numberOne.next = node2;
        numberTwo.next = null;
        return head;
    }
}
