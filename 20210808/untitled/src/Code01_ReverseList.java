/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 22:39
 */
public class Code01_ReverseList {
    //头插法
    public ListNode reverseLinkList1(ListNode head) {
        if (head == null || head.next == null) { //没有结点，或者只有一个结点的情况
            return head;
        }

        ListNode pre = null; //前驱结点
        ListNode cur = head; //当前结点
        ListNode next = null; //后驱结点
        while (cur != null) {
            next = cur.next; //首先保存后驱结点
            cur.next = pre; //改变链表的指向

            pre = cur; //pre和cur往下走一步
            cur = next;
        }
        return pre;
    }

    //三指针法---只是在头插法的基础之上改了一下。本质上没有什么区别
    public ListNode reverseLinkList2(ListNode head) {
        if (head == null || head.next == null) { //没有结点，或者只有一个结点的情况
            return head;
        }

        ListNode pre = null; //前驱结点
        ListNode cur = head; //当前结点
        ListNode next = null; //后驱结点
        ListNode newHead = null;
        while (cur != null) {
            next = cur.next; //首先保存后驱结点
            if (newHead == null) {
                newHead = cur;
            }
            cur.next = pre; //改变链表的指向

            pre = cur; //pre和cur往下走一步
            cur = next;
        }
        return pre;
    }

    //递归
    public ListNode reverseLinkList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return process(head, null); //第一次调用，上一结点就是null
    }

    public ListNode process(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode newHead = process(cur.next, cur); //递归调用下一结点
        cur.next = pre;  //连接pre
        return newHead; //将头结点返回去
    }
}
