import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-31
 * Time: 15:57
 * Description:h删除链表总和为0的数-LeetCode1171
 */
public class Code07_DelSum {

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null || (head.next == null && head.val == 0)) {
            return null;
        }

        //前缀和
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode node = new ListNode(0); //处理前缀和为0 的情况
        node.next = head;
        int sum = 0;
        for (ListNode cur = node; cur != null; cur = cur.next) {
            sum += cur.val;
            map.put(sum, cur); //将所有的前缀和放入map中
        }
        // 再次遍历链表。遇到两个前缀和的时候，两个值之间的链表就是和为0的时候
        sum = 0;
        for (ListNode cur = node; cur != null; cur = cur.next) {
            sum += cur.val;
            if (map.containsKey(sum)) {
                cur.next = map.get(sum).next; //连接上下一个前缀和位置的下一节点
            }
        }
        return node.next;
    }
}
