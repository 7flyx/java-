package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-14
 * Time: 11:34
 * Description: 4月14号 发邮件
 */
public class Code36_SendEMAIL {
    public static void main(String[] args) {
//        for (int i = 2; i < 11; i++) {
//            System.out.println(sendEMAIL(i));
//            System.out.println(process2(i));
//        }
        System.out.println(sendEMAIL(4));
        System.out.println(process2(4));

    }

    // 共有n个人
    public static long sendEMAIL(int n) {
        if (n <= 0) {
            return 0;
        }
        // 0位置舍弃不用
        int[] accept = new int[n + 1]; //表示i下标这个人接收了哪个人的邮件
        return process(accept, 1, n);
    }

    private static long process(int[] accept, int cur, int n) {
        if (cur > n) { // 全部发送完了邮件
            return 1;
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            // accept[i] =0,表示当前位置还没有邮件
            if (i != cur && accept[i] == 0) { // 当前邮件，不发送给自己
                accept[i] = cur; // 当前位置发cur的邮件
                res += process(accept, cur + 1, n);
                accept[i] = 0;
            }
        }
        return res;
    }

    public static long process2(int n) {
        long[] dp = new long[21];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i < 21; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
}
