/**
 * Created by flyx
 * Description: 判断链表是否有环
 * User: 听风
 * Date: 2021-09-05
 * Time: 9:25
 */
public class Code02_IsCirculList {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node cur = head;
        for (int i = 0; i < 10; i++) {
            cur.next = new Node(i + 2);
            cur = cur.next;
        }
        Node node = head;
        for (int i = 0; i < 5; i++) {
            node = node.next;
        }
        cur.next = node;

        System.out.println(isCirculList(head));
    }

    public static boolean isCirculList(Node node) {
        if (node == null || node.next == null) {
            return false;
        }

        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) { //相遇了，就是有环
                return true;
            }
        }
        return false;
    }
}
