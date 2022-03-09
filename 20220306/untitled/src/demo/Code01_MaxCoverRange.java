package demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-08
 * Time: 18:17
 * Description: 给你很多线段，线段的结果是[start, end](正整数)，即就是起点和终点。
 * 线段的长度>=1, 两个重叠点并不算重合区间，
 * 问你这个区间内，最多重叠的线段个数是多少。
 * 例如 给一个二维数组{[2, 10], [3, 6], [1, 8]}
 */
public class Code01_MaxCoverRange {
    // 普通解法-以某一个小数进行枚举，拿着枚举数去数组中统计
    public static int maxCover1(int[][] arr) {
        if (arr == null) {
            return 0;
        }
        int min = 0; // 起点的最小值
        int max = 0; // 终点的最大值
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i][0]); // 起点
            max = Math.max(max, arr[i][1]); //终点
        }

        int res = 0;
        for (double num = min + 0.5; num < max; num += 1) {
            // 以当前num去查表
            int tmp = 0;
            for (int i = 0; i < arr.length; i++) {
                if (num > arr[i][0] && num < arr[i][1]) { //在当前这个线段的范围内
                    tmp++;
                }
            }
            res = Math.max(res, tmp);
        }

        return res;
    }

    private static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int maxCover2(int[][] arr) {
        if (arr == null) {
            return 0;
        }
        // 以节点的形式，存储所有的起点终点
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        int res = 0;
        // 排序 实则就是为了搞一个贪心
        Arrays.sort(lines, (o1, o2) -> o1.start - o2.start); // 对于起点 排升序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //小根堆
        for (int i = 0; i < lines.length; i++) {
            // 拿到当前线段，以起点的边界，堆里小于这个边界的直接弹出
            while (!minHeap.isEmpty() && lines[i].start >= minHeap.peek()) {
                minHeap.poll(); //弹出堆顶元素
            }
            minHeap.add(lines[i].end); // 将终点压入堆中
            res = Math.max(res, minHeap.size()); //结算
        }
        return res;
    }

    public static void add(byte b) {
        b = b++;
    }

    public static void main(String[] args) {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.println(a);
        add(b);
        System.out.println(b);
    }


    public static void main1(String[] args) {
        int[][] arr = {{2, 10}, {3, 7}, {8, 9}, {5, 10}};
        System.out.println(maxCover1(arr));
        System.out.println(maxCover2(arr));
    }
}
