package bagproblems;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-12-01
 * Time: 22:09
 * Description: 宝物筛选，单调队列优化版本
 * https://www.luogu.com.cn/problem/P1776
 */
public class Code09_TreasureSelect2 {
    static int MAXN = 101;
    static int MAXM = 40001; // 最大的背包容量
    static int[] cost = new int[MAXN]; // 重量
    static int[] val = new int[MAXN]; // 价值
    static int[] cnt = new int[MAXN]; // 宝物的数量
    static int n, m; // 宝物数量、背包容量
    static int[] dp = new int[MAXM], help = new int[MAXM]; // 两张一维dp表交替

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                val[i] = (int) in.nval;
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                cnt[i] = (int) in.nval;
            }
            out.println(compute3());
        }
        br.close();
        out.flush();
        out.close();
    }
    // 枚举每一个物品的数量，时间超了
    private static int compute1() {
        // n个宝物，背包容量是m
        // dp[i][j] = k，表示在1~i范围的宝物任意选择，总容量不超过j，的最大价值是k
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int c = 0; c <= cnt[i] && c * cost[i] <= j; c++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c * cost[i]] + val[i] * c);
                }
            }
        }
        return dp[n][m];
    }

    // 二维dp表+单调队列优化，空间超了
    private static int compute2() {
        // n是宝物数量，m是背包容量
        int[][] dp = new int[n + 1][m + 1];
        // dp[i][j] = k,表示 1~i范围的宝物任意选择，重量不超过j，的最大价值是k
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            // 在物品重量区间进行尝试，假设物品重量=3，枚举就是  0 -> 3 -> 7 -> 10 -> 13
            // 在这上面使用单调队列进行优化，就能省去枚举行为
            for (int j = 0; j < cost[i]; j++) {
                q.clear();
                for (int r = j; r <= m; r += cost[i]) {
                    dp[i][r] = dp[i - 1][r]; // 不要当前物品了
                    // 以下就是要当前物品，具体需要第几个，看队头元素即可
                    // 这个是根据 应该一个具有枚举行为的dp优化得来的
                    while (!q.isEmpty() && dp[i - 1][q.peekLast()] + inc(r, q.peekLast(), cost[i], val[i]) <= dp[i][r]) {
                        q.pollLast();
                    }
                    q.addLast(r);
                    if ((r - q.peekFirst()) / cost[i] > cnt[i]) q.pollFirst(); // 队头元素过期了，弹出
                    dp[i][r] = Math.max(dp[i][r], dp[i - 1][q.peekFirst()] + inc(r, q.peekFirst(), cost[i], val[i]));
                }
            }
        }
        return dp[n][m];
    }


    // 一维dp表+单调队列优化
    private static int compute3() {
        // n是宝物数量，m是背包容量
        Arrays.fill(dp, 1, m + 1, 0);
        Arrays.fill(help, 1, m + 1, 0);
        // dp[i][j] = k,表示 1~i范围的宝物任意选择，重量不超过j，的最大价值是k
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            // 在物品重量区间进行尝试，假设物品重量=3，枚举就是  0 -> 3 -> 7 -> 10 -> 13
            // 在这上面使用单调队列进行优化，就能省去枚举行为
            for (int j = 0; j <= Math.min(cost[i] - 1, m); j++) {
                q.clear();
                for (int r = j; r <= m; r += cost[i]) {
                    help[r] = dp[r]; // 不要当前物品了
                    // 以下就是要当前物品，具体需要第几个，看队头元素即可
                    // 这个是根据 应该一个具有枚举行为的dp优化得来的
                    while (!q.isEmpty() && dp[q.peekLast()] + inc(r, q.peekLast(), cost[i], val[i]) <= help[r]) {
                        q.pollLast();
                    }
                    q.addLast(r);
                    if ((r - q.peekFirst()) / cost[i] > cnt[i]) q.pollFirst(); // 队头元素过期了，弹出
                    help[r] = Math.max(help[r], dp[q.peekFirst()] + inc(r, q.peekFirst(), cost[i], val[i]));
                }
            }
            int[] tmp = dp;
            dp = help;
            help = tmp;
        }
        return dp[m];
    }

    // 计算(l...r)重量范围内的物品的价值
    public static int inc(int r, int l, int cost, int val) {
        return (r - l) / cost * val;
    }
}