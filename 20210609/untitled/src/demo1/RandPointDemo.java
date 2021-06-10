package demo1;

public class RandPointDemo {
    public static void main(String[] args) {
        /*
            rand指针是单链表结点结构中新增的指针，rand可能指向链表的任意一个结点，也可能是null
            请你复制一个一模一样的链表出来
            解法一： 哈希表存储一样的结点，键值都是Node结点，
            解法二： 插入新结点到每一结点的后面，分别处理rand指针，再分离结点
         */

    }

    public static class Node {
        public int val;
        public Node next;
        public Node rand;

        public Node(int val) {
            this.val = val;
            next = null;
            rand = null;
        }
    }

    public static Node randLinkListCopy(Node head) {
        //赋值一个结点，插入到原先结点的后面，然后再设置rand指针，最后再分离新链表和旧链表
        // 1 -> 2
        // 1 -> 1' -> 2

        //插入新节点
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        //处理rand指针
        cur = head;
        Node copyHead = null;
        while (cur != null) {
            next = cur.next.next;
            copyHead = cur.next;
            copyHead.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        //split 分离新旧链表
        Node result = head.next; //先保存新链表的头结点
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyHead = cur.next;
            cur.next = next;
            copyHead.next = next != null? next.next : null;
            cur = next;
        }

        return result;
    }
}
