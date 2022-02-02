/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-02
 * Time: 9:40
 * Description: 合并两个有序链表
 */
public class Code10_MergeList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null? list2:list1;
        }

        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (list1 != null  && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null? list1 : list2;
        return res.next;
    }
}
