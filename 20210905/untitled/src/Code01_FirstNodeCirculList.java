/**
 * Created by flyx
 * Description: 找到第一个入环的节点
 * User: 听风
 * Date: 2021-09-05
 * Time: 9:10
 */
public class Code01_FirstNodeCirculList {


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

        System.out.println(getFirstNode(head).val);

    }



    public static Node getFirstNode (Node node) {
        if (node == null) {
            return null;
        }

        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                fast = node; //从头开始走
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null; //为null的情况
    }
}
