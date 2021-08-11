/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-11
 * Time: 15:34
 */
class Node {
    public int val;
    public Node next;

    public Node() {}
    public Node(int val) {
        this.val = val;
    }
}

public class MyLinkedList {
    private Node head;
    private Node tail; //尾指针

    //增加
    public void addFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        tail = tail == null? head : tail;
    }

    public void addLast(int val) {
        Node node = new Node(val);
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
    }

    //删除
    public void remove(int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        Node cur = head == null? null : head.next;
        Node pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public boolean searchNode(int val) {
        Node cur = head;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void display() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void reverseList() {
        Node pre = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;
    }


}
