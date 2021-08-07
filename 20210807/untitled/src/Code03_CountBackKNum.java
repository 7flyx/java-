/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-07
 * Time: 10:52
 */
public class Code03_CountBackKNum {
    public ListNode FindKthToTail(ListNode head,int k) {

        //相应的如果是顺序打印第k个值，很好理解
        //逆序打印第k个值？？相应的k的值也应该倒着走，k--
        ListNode slow = head;
        ListNode fast = head;
        for (; fast != null; fast = fast.next) {
            if (--k < 0) {
                slow = slow.next;
            }
        }
        return k <= 0? slow : null;
    }
}
