/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-31
 * Time: 15:55
 * Description:两数相加-LeetCode2
 */


 class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class Code06_TwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode res = null;
        ListNode cur = null;
        int carry = 0; //进位的数值
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (res == null) {
                res = cur = new ListNode(sum % 10);
            } else {
                cur.next = new ListNode(sum % 10);
                cur = cur.next;
            }
            carry = sum / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry); //最后进位的信息
        }
        return res;
    }
}
