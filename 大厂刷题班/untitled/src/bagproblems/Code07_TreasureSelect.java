package bagproblems;

import java.io.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-12-01
 * Time: 20:01
 * Description: 多种背包问题
 * 设 每个物品的数量最大是 W个。
 * 则时间复杂度为 O((log(第一个物品数量) + log(第二个物品数量) ... + log(第三个物品数量)) * M)
 * 也就是二进制 分组之后的“物品个数” * 背包容量
 * https://www.luogu.com.cn/problem/P1776
 */
public class Code07_TreasureSelect {
    static int MAXN = 1001;
    static int MAXM = 40001; // 最大的背包容量
    static int[] cost = new int[MAXN]; // 重量
    static int[] val = new int[MAXN]; // 价值
    static int[] cnt = new int[MAXN]; // 宝物的数量
    static int n, m; // 宝物数量、背包容量
    static long[] dp = new long[MAXM];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in =new StreamTokenizer(br);
        PrintWriter out =new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval; in.nextToken();
            m = (int)in.nval;
//            for (int i = 1; i <= n; i++) {
//                in.nextToken(); val[i] = (int)in.nval;
//                in.nextToken(); cost[i] = (int)in.nval;
//                in.nextToken(); cnt[i] = (int)in.nval;
//            }

            // 二进制分组，对每一个物品进行
            // 比如 i号物品有20个，就把20分解成二进制的形式
            // 比如 20 = 1 + 2 + 4 + 8 + 5 -> 1111 + 5
            // -> 上述的状态，每个位置进行01背包，就能枚举 0 ~ 20的所有可能性
            int len = 0; // 二进制分组后的数组长度
            for (int i = 1, v, w, c; i <= n; i++) {
                in.nextToken(); v = (int)in.nval;
                in.nextToken(); w = (int)in.nval;
                in.nextToken(); c = (int)in.nval;
                for (int k = 1; k <= c; k *= 2) {
                    cost[++len] = w * k;
                    val[len] = v * k;
                    cnt[len] = k;
                    c -= k;
                }
                if (c > 0) {
                    cost[++len] = w * c;
                    val[len] = v * c;
                    cnt[len] = c;
                }
            }
            n = len; // 二进制分组后，产生的新的长度
            out.println(compute2());
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

    // 二进制分组优化+ 空间压缩版本
    private static long compute2() {
        // n个宝物，背包容量是m
        // 二进制分组后，就是01背包了，不再是多重背包问题
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= cost[i]; j--) {
//                dp[i][j] = dp[i - 1][j]; // 当前位置的物品不要
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[m];
    }
}
