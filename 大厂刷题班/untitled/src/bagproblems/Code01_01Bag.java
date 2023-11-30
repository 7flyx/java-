package bagproblems;

import java.io.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 10:02
 * Description: 01背包
 * https://www.luogu.com.cn/problem/P1048
 */
public class Code01_01Bag {
    static int MAXM = 101;
    static int T, M;
    static int[] weight = new int[MAXM];
    static int[] val = new int[MAXM];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//        while (in.nextToken() != StreamTokenizer.TT_EOF) {
        in.nextToken();
        T = (int) in.nval;
        in.nextToken();
        M = (int) in.nval;
        for (int i = 1; i <= M; i++) {
            in.nextToken();
            weight[i] = (int) in.nval;
            in.nextToken();
            val[i] = (int) in.nval;
        }
        out.println(compute());
//        }
        out.flush();
        br.close();
        out.close();
    }

    private static int compute() {
        // 01背包问题，每个物品选择要与不要两种情况
//        System.out.println("hello ");
        int n = T, m = M;
        // dp[i][j] = k, 表示 1~i范围的物品任意选，在重量不超过 j的情况下，获得的最大价值
        int[][] dp = new int[m + 1][n + 1];
        // base case: 第0行就是没有物品，所以价值是0
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 选择要、不要两种情况
                dp[i][j] = dp[i - 1][j];
                if (j - weight[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + val[i]);
                }
            }
        }
        return dp[m][n];
    }

    // 空间压缩
    private static int compute2() {
        int n = M, m = T;
        int[] dp = new int[m + 1];
        // dp第0行就是0个商品的价值
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + val[i]);
            }
        }
        return dp[m];
    }
}
