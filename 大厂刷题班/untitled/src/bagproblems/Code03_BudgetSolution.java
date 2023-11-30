package bagproblems;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 12:05
 * Description: 预算方案
 * 有依赖的背包问题
 * https://www.luogu.com.cn/problem/P1064
 */
public class Code03_BudgetSolution {
    static int MAXN = 32001; // 金额
    static int MAXM = 61; // 物品数量
    static int[] cost = new int[MAXM]; // 成本
    static int[] val = new int[MAXM]; // 价值
    static int[] up = new int[MAXM]; // =0时，自己就是主键。!=0时，表示 cost[j]是他的主件
    static boolean[] isKing = new boolean[MAXM];
    static int[][] fans = new int[MAXM][2]; // 主件的附件是哪两个
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for (int i = 1; i <= m; i++) Arrays.fill(fans[i], -1);
            for (int i = 1; i <= m; i++) {
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval * cost[i];
                in.nextToken();
                up[i] = (int) in.nval;
                isKing[i] = up[i] == 0;
                if (!isKing[i]) {
                    if (fans[up[i]][0] == -1) fans[up[i]][0] = i;
                    else fans[up[i]][1] = i;
                }
            }
            out.println(compute2());
        }

        out.flush();
        out.close();
        br.close();
    }

    private static int compute1() {
        // 一共m件商品，想要买附件，必须也要买对应的主件的情况下
        // 不超过 n的钱数，返回最大的价值
        // 有依赖的背包问题
        // 思路：以主件为载体进行枚举，每个主件要、不要两种情况，如果要了主件、接着枚举他对应的附件的情况
        // dp[i][j] = k, 表示 在 0~i范围的主件商品任意选择，不超过j的钱数，能得到的最大价值
        int[][] dp = new int[m + 1][n + 1];
        int pre = 0; // 上一个主件的位置
        for (int i = 1; i <= m; i++) {
            if (!isKing[i]) continue; // 物品下标是从0开始的，不是主件就跳过
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[pre][j]; // 不要当前物品
                int f1 = fans[i][0], f2 = fans[i][1];
                if (j - cost[i] >= 0) { // 要了当前物品
                    dp[i][j] = Math.max(dp[i][j], dp[pre][j - cost[i]] + val[i]);
                    // 枚举附件
                    if (f1 != -1 && j - cost[i] - cost[f1] >= 0) { // 只要第1个附件
                        dp[i][j] = Math.max(dp[i][j], dp[pre][j - cost[i] - cost[f1]] + val[f1] + val[i]);
                    }
                    if (f2 != -1 && j - cost[i] - cost[f2 ] >= 0) { // 只要第2个附件
                        dp[i][j] = Math.max(dp[i][j], dp[pre][j - cost[i] - cost[f2]] + val[f2] + val[i]);
                    }
                    if (f1 != -1 && f2 != -1) { // 两个附件都要了
                        int nextJ = j - cost[i] - cost[f1] - cost[f2];
                        if (nextJ >= 0) {
                            dp[i][j] = Math.max(dp[i][j],
                                    dp[pre][nextJ] + val[f1] + val[f2] + val[i]);
                        }
                    }
                }
            }
            pre = i;
        }
        return dp[pre][n];
    }

    // 空间压缩版本
    private static int compute2() {
        // 一共m件商品，想要买附件，必须也要买对应的主件的情况下
        // 不超过 n的钱数，返回最大的价值
        // 有依赖的背包问题
        // 思路：以主件为载体进行枚举，每个主件要、不要两种情况，如果要了主件、接着枚举他对应的附件的情况
        // dp[i][j] = k, 表示 在 0~i范围的主件商品任意选择，不超过j的钱数，能得到的最大价值
        int[] dp = new int[n + 1];
        int pre = 0; // 上一个主件的位置
        for (int i = 1; i <= m; i++) {
            if (!isKing[i]) continue; // 物品下标是从0开始的，不是主件就跳过
            for (int j = n; j >= 0; j--) {
//                dp[j] = dp[j]; // 不要当前物品
                int f1 = fans[i][0], f2 = fans[i][1];
                if (j - cost[i] >= 0) { // 要了当前物品
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
                    // 枚举附件
                    if (f1 != -1 && j - cost[i] - cost[f1] >= 0) { // 只要第1个附件
                        dp[j] = Math.max(dp[j], dp[j - cost[i] - cost[f1]] + val[f1] + val[i]);
                    }
                    if (f2 != -1 && j - cost[i] - cost[f2 ] >= 0) { // 只要第2个附件
                        dp[j] = Math.max(dp[j], dp[j - cost[i] - cost[f2]] + val[f2] + val[i]);
                    }
                    if (f1 != -1 && f2 != -1) { // 两个附件都要了
                        int nextJ = j - cost[i] - cost[f1] - cost[f2];
                        if (nextJ >= 0) {
                            dp[j] = Math.max(dp[j],
                                    dp[nextJ] + val[f1] + val[f2] + val[i]);
                        }
                    }
                }
            }
            pre = i;
        }
        return dp[n];
    }
}
