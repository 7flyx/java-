import java.io.*;
/**
 * Created by flyx
 * Description: 一种怪异结点的删除方式
 * User: 听风
 * Date: 2021-08-08
 * Time: 18:43
 */


public class Code11_DelStrangerNode {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        String[] values = sc.readLine().split(" ");
        int k = Integer.parseInt(sc.readLine());
        Node head = new Node(0);
        Node cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new Node(Integer.parseInt(values[i]));
            cur = cur.next;
        }
        head = head.next;
        cur = head;
        for (int i = 1; i < k; i++) {
            cur = cur.next;
        }
        delStrangerNode(cur);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        sc.close();
    }

    public static class Node {
        public int val;
        public Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public static void delStrangerNode(Node head) {
        if (head == null) {
            return;
        }
        //没办法删除的，只有进行数据的覆盖
        Node next = head.next;
        if (next == null) {
            throw new RuntimeException("can not remove last node.");
        }
        head.val = next.val;
        head.next = next.next;
    }
}
