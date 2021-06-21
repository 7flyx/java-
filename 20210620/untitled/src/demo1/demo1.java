package demo1;

public class demo1 {
    public static void main(String[] args) {
        //链表面试题2
    /*
        将单链表按照某值，小于的该值的放前面，等于的在中间，大于的在后面
        解法一： 放到一个数组，做partition ，荷兰国旗问题 （笔试用）
        解法二： 分为三个部分，每个部分创建头尾指针，遍历单链表，最后 小于区尾巴连 等于区头   ，等于区的尾巴 连 大于区的头
     */

        Node head = new Node();
        Node tail = head;
        int[] arr = {10, 30, 50, 20, 90, 50, 70, 50};
        for (int i = 0; i < arr.length; i++) {
            tail.val = arr[i];
            if (i != arr.length - 1) {
                tail.next = new Node();
                tail = tail.next;
            }
        }

        Node slow = partiton2(head,50);
        while (slow != null) {
            System.out.print(slow.val + " ");
            slow = slow.next;
        }

    }

    public static class Node {
        public int val;
        public Node next;

        public Node() {
            val = 0;
            next = null;
        }
    }

    public static Node partiton2(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        Node sH = null;
        Node sT = null; //小于区
        Node eH = null;
        Node eT = null; //等于区
        Node bH = null;
        Node bT = null; //大于区

        //遍历单链表
        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.val > k) { //大于区
                if (bT == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = bT.next;
                }
            } else if (head.val < k) { //小于区
                if (sT == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else { //等于区
                if (eT == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            }
            head = next;
        }

        //连接三个区域
        if (sT != null) { //判断小于区
            sT.next = eH;
            eT = eT==null? sT: eT; //如果为空，需要改变eT的指向
        }
        if (eT != null) { //判断大于区
            eT.next = bH;
        }
        return sH != null? sH: (eH != null? eH : bH);
    }

}
