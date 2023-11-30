package bagproblems;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-11-30
 * Time: 19:13
 * Description: 分组背包
 * https://www.luogu.com.cn/problem/P1757
 */
public class Code04_GroupingBag {
    static int MAXN = 1001;
    static int[][] nums = new int[MAXN][3];
    static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1, weight, val, group; i <= n; i++) {
                in.nextToken();
                nums[i][0] = (int) in.nval;
                in.nextToken();
                nums[i][1] = (int) in.nval;
                in.nextToken();
                nums[i][2] = (int) in.nval;
            }
            out.println(compute2());
        }

        out.close();
        out.close();
        br.close();
    }

    private static int compute1() {
        // 分组背包，每个组内的数据，只能选择其中一个。返回钱数 不超过 m，得到的最大价值
        Arrays.sort(nums, 1, n + 1, ((o1, o2) -> o1[2] - o2[2])); // 按照组进行排序
        int cnt = 1;
        for (int i = 1; i < n; i++) { // 统计组数
            if (nums[i][2] != nums[i + 1][2]) cnt++;
        }
        // dp[i][j] = k, 表示 在1~i组任意选择，钱数不超过j，能获得的最大价值
        int[][] dp = new int[cnt + 1][m + 1];
        // dp[0][...] 0组都没有商品，价值都是0
        for (int c = 1, start = 1, end; c <= cnt; c++) { // 组数
            end = start + 1;
            while (end <= n && nums[end][2] == nums[end - 1][2]) end++;
            // 此时 start~end-1，就是同一个组的数据
            for (int i = start; i < end; i++) {
                for (int j = 0; j <= m; j++) {
                    dp[c][j] = Math.max(dp[c][j], dp[c - 1][j]); // 不要当前i位置的物品
                    if (j - nums[i][0] >= 0) { // 要了当前i位置的物品
                        dp[c][j] = Math.max(dp[c][j], dp[c - 1][j - nums[i][0]] + nums[i][1]);
                    }
                }
            }
            start = end;
        }
        return dp[cnt][m];
    }

    // 空间压缩
    private static int compute2() {
        // 分组背包，每个组内的数据，只能选择其中一个。返回钱数 不超过 m，得到的最大价值
        Arrays.sort(nums, 1, n + 1, ((o1, o2) -> o1[2] - o2[2])); // 按照组进行排序
        int cnt = 1;
        for (int i = 1; i < n; i++) { // 统计组数
            if (nums[i][2] != nums[i + 1][2]) cnt++;
        }
        // dp[i][j] = k, 表示 在1~i组任意选择，钱数不超过j，能获得的最大价值
        int[] dp = new int[m + 1];
        int[] help = new int[m + 1]; // 辅助数组
        // dp[0][...] 0组都没有商品，价值都是0
        for (int c = 1, start = 1, end; c <= cnt; c++) { // 组数
            end = start + 1;
            Arrays.fill(help, 0);
            while (end <= n && nums[end][2] == nums[end - 1][2]) end++;
            // 此时 start~end-1，就是同一个组的数据
            for (int i = start; i < end; i++) {
                for (int j = m; j >= 0; j--) {
                    help[j] = Math.max(help[j], dp[j]); // 不要当前i位置的物品
                    if (j - nums[i][0] >= 0) { // 要了当前i位置的物品
                        help[j] = Math.max(help[j], dp[j - nums[i][0]] + nums[i][1]);
                    }
                }
            }
            start = end;
            int[] tmp = dp;
            dp = help;
            help = tmp;
        }
        return dp[m];
    }
}
