/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-09-05
 * Time: 11:07
 */

public class Code06_PartListNode {
    public Node partition(Node pHead, int x) {
        // write code here
        //类似于快排中的partition操作
        Node leftHead = null;
        Node leftTail = null;
        Node rightHead = null;
        Node rightTail = null;

        while (pHead != null) {
            Node next = pHead.next;
            pHead.next = null;
            if (pHead.val < x) {
                if (leftTail == null) {
                    leftHead = pHead;
                    leftTail = pHead;
                } else {
                    leftTail.next = pHead;
                    leftTail = pHead;
                }
            } else {
                if (rightTail == null) {
                    rightHead = pHead;
                    rightTail = pHead;
                } else {
                    rightTail.next = pHead;
                    rightTail = pHead;
                }
            }
            pHead = next;
        }

        if (leftTail != null) {
            leftTail.next = rightHead;
            return leftHead;
        }
        return rightHead;
    }
}
