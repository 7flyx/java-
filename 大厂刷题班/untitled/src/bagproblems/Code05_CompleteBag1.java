package bagproblems;

import java.io.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 21:02
 * Description: 完全背包
 * https://www.luogu.com.cn/problem/P1616
 */
public class Code05_CompleteBag1 {
    static int MAXM = 10001;
    static int[] cost = new int[MAXM];
    static int[] val = new int[MAXM];
    static int t, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int)in.nval; in.nextToken();
            m = (int)in.nval;
            for (int i = 1; i <= m; i++) {
                in.nextToken(); cost[i] = (int)in.nval;
                in.nextToken(); val[i] = (int)in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    private static long compute1() {
        // 完全背包，每个物品可以选择任意次
        long[][] dp = new long[m + 1][t + 1];
        // dp[i][j] = k, 表示 1~i范围的物品任意选择多次，在成本不超过j的情况下的，最大值
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - cost[i]] + val[i]);
                }
            }
        }
        return dp[m][t];
    }

    private static long compute2() {
        // 完全背包，每个物品可以选择任意次
        long[] dp = new long[t + 1];
        // dp[i][j] = k, 表示 1~i范围的物品任意选择多次，在成本不超过j的情况下的，最大值
        for (int i = 1; i <= m; i++) {
            for (int j = cost[i]; j <= t; j++) {
//                dp[i][j] = dp[i - 1][j];
//                if (j - cost[i] >= 0)
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[t];
    }
}
