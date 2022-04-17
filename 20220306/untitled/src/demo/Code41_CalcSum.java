package demo;

import java.util.LinkedList;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-17
 * Time: 21:21
 * Description: 求和
 * https://www.nowcoder.com/questionTerminal/11cc498832db489786f8a03c3b67d02c
 */
public class Code41_CalcSum {
    public static void main(String[] args) {
        System.out.println(calcSum(6, 8));
    }

    public static String calcSum(int n, int m) {
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();
        process(n, m, 0, sb, queue);
        return sb.toString();
    }

    public static void process(int n, int m, int cur, StringBuilder sb, LinkedList<Integer> queue) {
        if (m == 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                sb.append(queue.get(i));
                if (i + 1 < size) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            return;
        }
        if (m < 0) {
            return;
        }
        for (int i = cur + 1; i <= n; i++) {
            queue.addLast(i);
            process(n, m - i, i, sb, queue);
            queue.removeLast(); // 删除后进入的
        }
    }
}
