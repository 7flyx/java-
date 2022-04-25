package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-22
 * Time: 22:27
 * Description: 4月22号 第二个代码题 马戏团
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Code47_Circus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] arr = new int[n][3];
            for (int i = 0; i < n; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
                arr[i][2] = sc.nextInt();
            }

            System.out.println(calcMaxLevel(arr));
        }
    }

    public static int calcMaxLevel(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 大根堆
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.weight == o2.weight) {
                return o2.height - o1.height;
            }
            return o2.weight - o1.weight;
        });

        for (int i = 0; i < arr.length; i++) {
            queue.add(new Node(arr[i][1], arr[i][2]));
        }

        int res = 1;
        Node cur = queue.poll(); // 弹出最大的一个元素
        for (int i = 1; i < arr.length; i++) {
            Node node = queue.poll();
            if (node.height <= cur.height && node.weight <= cur.weight) {
                res++;
                cur = node;
            }
        }

        return res;
    }

    static class Node {
        public int height;
        public int weight;

        public Node(int h, int w) {
            this.weight = w;
            this.height = h;
        }
    }

}
