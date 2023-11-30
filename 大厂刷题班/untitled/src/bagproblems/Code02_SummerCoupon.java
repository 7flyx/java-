package bagproblems;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 10:32
 * Description: 01背包
 * https://leetcode.cn/problems/tJau2o/
 */
public class Code02_SummerCoupon {
    static int MAXN = 501;
    static int MAXM = 10001;
    static int N, M, X; // N 是商品个数，M的预算的金额
    static int[] cost = new int[MAXN]; // 现价
    static int[] value = new int[MAXN]; // 价值

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
//        in.nextToken();
            N = (int) in.nval;
            in.nextToken();
            X = (int) in.nval;
            int ans = 0; // 最终的结果
            M = 1;
            List<int[]> list = new ArrayList<>();
            list.add(new int[]{0, 0}); // 填补0下标，好计算
            for (int i = 1, pre, cur, val; i <= N; i++) {
                in.nextToken();
                pre = (int) in.nval;
                in.nextToken();
                cur = (int) in.nval;
                in.nextToken();
                val = (int) in.nval;
                int div = pre - cur - cur;
                if (div >= 0) { // 对于 降价的物品，本质上是拉高了预算的，这种商品就必选
                    ans += val;
                    M += div; // 拉高了预算金额
                } else {
                    cost[M] = -div;
                    value[M++] = val;
                }
            }
            ans += compute1();
            out.println(ans);
        }
        out.flush();
        br.close();

    }

    private static int compute1() {
        int[][] dp = new int[M + 1][X + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= X; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + value[i]);
                }
            }
        }
        return dp[M][X];
    }
}
