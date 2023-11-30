package bagproblems;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 22:10
 * Description: 完全背包
 * https://www.luogu.com.cn/problem/P2918
 */
public class Code06_CompleteBag2 {
    static int MAXH = 50001;
    static int MAXN = 101;
    static int[] cost = new int[MAXN];
    static int[] val = new int[MAXN];
    static int N, H; // 公司数量、预计的甘草数量
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int)in.nval; in.nextToken();
            H = (int)in.nval;
            for (int i = 1; i <= N; i++) {
                in.nextToken(); val[i] = (int)in.nval;
                in.nextToken(); cost[i] = (int)in.nval;
            }
            out.println(compute2());
        }

        br.close();
        out.flush();
        out.close();
    }

    private static int compute1() {
        int max = 0;
        for (int i = 1; i <= N; i++) max = Math.max(max, val[i]);
        int[][] dp = new int[N + 1][max + H + 1];
        // dp[i][j] = k，表示 1~i范围的物品，任意选择多次，凑齐 j榜甘草，最少花费多少钱
        // 除了[0][0]的最小花费是0，其他的j列都是达不到的，都是无效值
        Arrays.fill(dp[0], 1, max + H + 1, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= max + H; j++) {
                dp[i][j] = dp[i - 1][j]; // 当前不要i位置的物品
                if (j - val[i] >= 0 && dp[i][j - val[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - val[i]] + cost[i]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = H; i <= max + H; i++) {
            ans = Math.min(ans, dp[N][i]);
        }
        return ans;
    }

    // 空间压缩
    private static int compute2() {
        int max = 0;
        for (int i = 1; i <= N; i++) max = Math.max(max, val[i]);
        int[] dp = new int[max + H + 1];
        // dp[i][j] = k，表示 1~i范围的物品，任意选择多次，凑齐 j榜甘草，最少花费多少钱
        // 除了[0][0]的最小花费是0，其他的j列都是达不到的，都是无效值
        Arrays.fill(dp, 1, max + H + 1, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            for (int j = val[i]; j <= max + H; j++) {
//                dp[i][j] = dp[i - 1][j]; // 当前不要i位置的物品
                if (dp[j - val[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - val[i]] + cost[i]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = H; i <= max + H; i++) {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }
}
