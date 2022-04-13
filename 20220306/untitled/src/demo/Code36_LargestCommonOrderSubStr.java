package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-13
 * Time: 11:39
 * Description: 4月13号 第二个代码题 最长公共子序列
 */
public class Code36_LargestCommonOrderSubStr {
    public static void main(String[] args) {
        String s1 = "1A2C3D4B56";
        String s2 = "B1D23CA45B6A";
        System.out.println(largestCommonOrderSubStr2(s1, s2));
    }

    // 经典dp
    public static int largestCommonOrderSubStr1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n][m];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], s1.charAt(i) == s2.charAt(0) ? 1 : 0);
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], s1.charAt(0) == s2.charAt(j) ? 1 : 0);
        }

        // 填写普遍位置
        // 计算dp[i][j] 位置最优的情况，分别来自于三个方向
        // 左边 上边 和 左上角，三个情况
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 左边和上边先取最优结果
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    // dp空间压缩
    public static int largestCommonOrderSubStr2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        int n = s1.length();
        int m = s2.length();
        if (n < m) {
            int[] dp = new int[n];
            dp[0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
            // 填写第一列
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], s1.charAt(i) == s2.charAt(0) ? 1 : 0);
            }
            for (int i = 1; i < m; i++) { // 行
                int leftUp = dp[0]; // 左上角的值
                dp[0] = Math.max(dp[0], s1.charAt(0) == s2.charAt(i) ? 1 : 0); // 第一行的情况
                for (int j = 1; j < n; j++) { // 列
                    int up = dp[j]; // 上边的值
                    dp[j] = Math.max(dp[j], dp[j - 1]); // 上边和左边的值，取最优
                    if (s2.charAt(i) == s1.charAt(j)) {
                        dp[j] = Math.max(dp[j], leftUp + 1);
                    }
                    leftUp = up; // 更新左上角的值
                }
            }
            return dp[n - 1];
        }

        int[] dp = new int[m];
        dp[0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int j = 1; j < m; j++) {
            dp[j] = Math.max(dp[j - 1], s1.charAt(0) == s2.charAt(j) ? 1 : 0);
        }
        // 填写普遍位置
        for (int i = 1; i < n; i++) {
            int leftUp = dp[0];
            dp[0] = Math.max(dp[0], s1.charAt(i) == s2.charAt(0)? 1 : 0); // 第一列的情况
            for (int j = 1; j < m; j++) {
                int up = dp[j];
                dp[j] = Math.max(dp[j], dp[j - 1]); // 左边和上边的值，取最优结果
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[j] = Math.max(dp[j], leftUp + 1);
                }
                leftUp = up;
            }
        }
        return dp[m - 1];
    }
}
