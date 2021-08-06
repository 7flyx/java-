package demo;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-06
 * Time: 10:02
 */

class Node {
    public int val;
    public Node next;//null

    public Node(int val) {
        this.val = val;
    }
}
//链表
public class MyLinkedList {
    public Node head;//标识单链表的头节点


    /**
     * 穷举的方式 创建链表  当然很low。此处只是为了好理解
     */
    public void createList() {
        Node node1 = new Node(12);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        Node node4 = new Node(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        this.head = node1;
    }

    /**
     * 打印单链表
     */
    public void show() {
        Node cur = this.head;
        while(cur != null) {
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    //得到单链表的长度
    public int size() {
        if (this.head == null) {
            throw new RuntimeException ("linkedList is empty.");
        }
        Node cur = this.head;
        int count = 0;
        while(cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        if (this.head == null) {
            throw new RuntimeException ("linkedList is empty.");
        }
        Node cur = this.head;
        while (cur != null) {
            if(cur.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public Node searchPrevNode(int val) {
        Node pre = null;
        Node cur = this.head;
        while (cur != null) {
            if (cur.val == val) {
                return pre;
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }

    //头插法
    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = this.head;
        this.head = node;
    }
    //尾插法
    public void addLast(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        } else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }
    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data){

    }

    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        Node pre = searchPrevNode(key);
        if (pre == null) {
            this.head = this.head == null? null : this.head.next;
        } else {
            pre.next = pre.next.next;
        }
    }
    //删除所有值为key的节点
    public void removeAllKey(int key) {
        while (this.head != null && this.head.val == key) {
            this.head = this.head.next; //换头结点的情况
        }
        Node pre = this.head;
        Node cur = this.head == null? null : this.head.next;
        while (cur != null) {
            if (cur.val == key) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public void clear(){

    }



}
