/**
 * Created by flyx
 * Description: 链表题的练习
 * User: 听风
 * Date: 2021-09-01
 * Time: 9:47
 */
public class MyLinkedList {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private ListNode tail;

    public void addFirst(int val) {
        if (head == null) {
            head = new ListNode(val);
            tail = head;
        } else {
            ListNode node = new ListNode(val);
            node.next = head;
            head = node;
        }
    }

    //判断是否有环
    public boolean chkPalindrome(ListNode A) {
        // write code here
        if (A == null || A.next == null) {
            return true;
        }
        ListNode slow = A;
        ListNode fast = A.next;
        //slow始终指向的中间节点的上一节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rightNode = reverseList(slow.next);

        slow = A; //从头开始
        fast = rightNode;
        boolean res = true;
        while (slow != fast) {
            if (slow.val != fast.val) {
                res = false;
                break;
            }
            if (slow.next == fast) { //偶数节点的情况
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }
        reverseList(rightNode); //恢复链表
        return res;
    }

    private ListNode reverseList(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    //返回循环链表的第一个入环节点
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == head) {
            return head;
        }

        //快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        //停下来，就是相遇的情况，或者就不是循环链表
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //返回相交节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode node1 = headA;
        ListNode node2 = headB;
        int k = 0;
        while (node1 != null) {
            node1 = node1.next;
            k++;
        }
        while (node2 != null) {
            node2 = node2.next;
            k--;
        }

        node1 = k > 0? headA : headB; //长链表
        node2 = node1 == headA? headB : headA; //短链表
        k = Math.abs(k); //取绝对值
        while (k-- > 0) {
            node1 = node1.next;
        }
        while (node1 != null && node2 != node1) { //还剩下想相同长度的链表，往后遍历即可
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }
}
