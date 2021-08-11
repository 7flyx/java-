/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-11
 * Time: 15:30
 */



public class Demo {
    public static void main(String[] args) {
        //实现单链表 的创建
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < 5; i++) {
            list.addLast(i + 1);
        }
        list.display();
        list.reverseList();
        list.display();
    }

    public static Node returnMidNode(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
