package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-31
 * Time: 9:49
 * Description:
 */
public class Code26_RaiseRabbit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            String[] arr = sc.nextLine().split("<br/>");
            for (int i = 0; i < arr.length; i++) {
                sb.append(getRabbitSum(Integer.parseInt(arr[i])));
                if (i + 1 < arr.length) {
                    sb.append("<br/>");
                }
            }
            System.out.println(sb.toString());
        }
    }

    // 本质上还是斐波那契数
    public static long getRabbitSum(int n) {
        if (n < 0) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }

        long pre = 1;
        long cur = 0;
        long tmp = 0;
        for (int i = 2; i <= n; i++) {
            tmp = cur + pre;
            pre = cur;
            cur = tmp;
        }
        return cur;
    }
}
