/**
 * Created by flyx
 * Description: 删除有序链表中的重复节点，重复节点不保留
 * User: 听风
 * Date: 2021-09-05
 * Time: 10:21
 */
public class Code05_DelRepeatNode {



    public static Node delRepeatNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null; //记录前驱节点
        Node cur = head;
        Node next = cur.next;
        boolean flag = false; //表示暂时出现重复值了吗？
        while (next != null) {
            if (cur.val == next.val) {
                flag = true;
                next = next.next;
                cur.next = next;
            } else {
                if (!flag) { //当前值不相等，前面也没有出现重复值的情况
                    pre = cur;
                    cur = next;
                } else if (pre != null) { //出现了重复值，但pre不为null
                    pre.next = next;
                    cur = next;
                } else { //出现了重复值，但pre为null的情况，则需要该头节点
                    head = next;
                    cur = next;
                }
                next = next.next;
                flag = false;
            }
        }
        if (flag) { //最后一个节点的情况
            if (pre != null) {
                pre.next = null;
            } else {
                head = null;
            }
        }
        return head;
    }
}
