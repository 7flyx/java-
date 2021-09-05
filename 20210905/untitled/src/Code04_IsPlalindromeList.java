/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-09-05
 * Time: 9:43
 */
public class Code04_IsPlalindromeList {


    public static void main(String[] args) {
        int[] array = {1,2,3,2,1};
        Node head = new Node(0);
        Node cur = head;
        for (int i = 0; i < array.length; i++) {
            cur.next = new Node(array[i]);
            cur = cur.next;
        }
        head = head.next;
        System.out.println(isPlalindromeList(head));
    }

    public static boolean isPlalindromeList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        //反转后半部分的链表，然后判断
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = slow.next; //中间节点
        Node rightHead = reverseList(slow);
        boolean res = true;
        while (rightHead != head) {
            if (rightHead.val != head.val) {
                res = false;
                break;
            }
            if (head.next == rightHead) { //偶数节点情况
                break;
            }
            rightHead = rightHead.next;
            head = head.next;

        }
        reverseList(slow); //恢复链表
        return res;
    }

    public static Node reverseList(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
