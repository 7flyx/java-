/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-08
 * Time: 14:41
 */


import java.io.*;

public class Code06_ListPartition {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = sc.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        int pivot = Integer.parseInt(nums[1]);

        String[] values = sc.readLine().split(" ");
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(Integer.parseInt(values[i]));
            cur = cur.next;
        }
        head = head.next;

        head = listPartition2(head, pivot);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    //数组做partition
    public static ListNode listPartition1(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int count = 0; //计算链表的长度
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        ListNode[] arr = new ListNode[count];
        for (int i = 0; i < count; i++) { //将所有结点放入数组
            arr[i] = head;
            head = head.next;
        }

        int left = -1; //小于区域的范围
        int right = count; //大于区域的范围
        int index = 0; //用于遍历数组的下标
        while (index < right) { //只要index没有和大于区域相遇，循环就继续
            if (arr[index].val == pivot) {
                index++;  //等于区域，别动，index往后走即可
            } else if (arr[index].val > pivot) {
                swap(arr, index, --right); //切记，这里index还不能动。因为从后面拿前来的数据，还没有判断大小
            } else {
                swap(arr, index++, ++left);
            }
        }

        for (int i = 1; i < count; i++) { //连接每个结点
            arr[i - 1].next = arr[i];
        }
        arr[count - 1].next = null; //最后一个结点的next，赋值null
        return arr[0];
    }

    public static void swap(ListNode[] arr, int left, int right) {
        ListNode tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    //六个引用变量
    public static ListNode listPartition2(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sH = null;
        ListNode sT = null; //小于区域

        ListNode eH = null;
        ListNode eT = null; //等于区域

        ListNode bH = null;
        ListNode bT = null; //大于区域

        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head; //尾结点去连接
                    sT = head;
                }
            } else if (head.val == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head; //尾结点去连接
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head; //尾结点去连接
                    bT = head;
                }
            }
            head = next; //往后走
        }

        //连接三个区域
        if (sH != null) {
            sT.next = eH;
            eT = eH == null? sT : eT; //判断等于区域是否有结点
        }
        if (bH != null) {
            eT.next = bH;
        }
        return sH != null? sH : (eH != null? eH : bH);
    }
}
