/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 17:32
 */

class Node {
    public int val;
    public Node next;
    public Node random;
    public Node(int val) {
        this.val = val;
    }
}
public class Code09_CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        //第一步-复制结点
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node node = new Node(cur.val);

            node.next = next; //连接下一结点
            cur.next = node; //新结点挂在旧结点的后面
            cur = next;
        }

        //第二步 - random指针
        cur = head;
        Node res = cur.next; //复制链表的头结点
        Node copy = null;
        while (cur != null) {
            Node next = cur.next.next; //下一个旧结点
            copy = cur.next;
            copy.random = cur.random == null? null : cur.random.next; //连接random指针,需要注意null
            cur = next;
        }

        //第三步-分离
        cur = head;
        copy = res; //指向复制链表的第一个结点
        while (cur != null) {
            Node next = cur.next.next; //下一个旧结点
            copy.next = next == null? null : next.next; //注意，这里需要判断是否为null
            cur.next = next; //将原来的链表还原

            copy = copy.next;
            cur = next;
        }
        return res;
    }
}



