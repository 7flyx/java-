package bagproblems;

import java.io.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-12-01
 * Time: 20:58
 * Description: 多重背包
 * https://www.luogu.com.cn/problem/P1833
 */
public class Code08_AppreciateSakura {
    static int MAXT = 1001;
    static int ENOUGH = 1001; // 能看的最长时间
    static int MAXN = 100001;
    static int[] cost = new int[MAXN];
    static int[] val = new int[MAXN];
    static int t, n, startH, startM, endH, endM; // 看的时间、花的数量
    static int[] dp = new int[MAXN];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in =new StreamTokenizer(br);
        PrintWriter out =new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            startH = (int)in.nval; in.nextToken(); in.nextToken();
            startM = (int)in.nval; in.nextToken();
            endH = (int)in.nval; in.nextToken(); in.nextToken();
            endM = (int)in.nval; in.nextToken();
            n = (int)in.nval;
            t = (endH - startH) * 60 + (endM - startM); // 总的时间数
            int len = 0;
            for (int i = 1, v, w, c; i <= n; i++) {
                in.nextToken(); w = (int)in.nval; // 耗费的时间
                in.nextToken(); v = (int)in.nval; // 价值
                in.nextToken(); c = (int)in.nval; // 能看的次数
                if (c == 0) c = ENOUGH; // =0，表示可以看无数次，但总时间最大就是1000，所以这里最大就是1000
                for (int k = 1; k <= c; k *= 2) {
                    cost[++len] = w * k;
                    val[len] = v * k;
                    c -= k;
                }
                if (c > 0) {
                    cost[++len] = w * c;
                    val[len] = v * c;
                }
            }
            n = len; // 二进制分组后，产生的新的长度
            out.println(compute1());
        }
        br.close();
        out.flush();
        out.close();
    }

    // 二进制分组优化+空间压缩版本
    private static int compute1() {
        // t是总时间，n = 物品个数
        // dp[i][j] = k, 表示 1~i的物品任意选择，不超过j的时间数，获得的最大价值是k
        // 本来题意应该是多重背包问题，然后进行二进制分组后，就是01背包问题
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + val[i]);
            }
        }
        return dp[t];
    }
}
