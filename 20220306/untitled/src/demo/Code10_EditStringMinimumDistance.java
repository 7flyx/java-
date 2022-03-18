package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-17
 * Time: 21:02
 * Description: 3月18号 第二个代码题
 */
public class Code10_EditStringMinimumDistance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        System.out.println(str1);
        System.out.println(str2);

        System.out.println(getMinimumEditDistance(str1, str2));
    }

    // 经典dp
    public static int getMinimumEditDistance(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int N = str1.length();
        int M = str2.length();
        int[][] dp = new int[N + 1][M + 1];
        dp[0][0] = 0; //二者都是空串的情况
        for (int i = 1; i <= M; i++) {
            dp[0][i] = i; // 由str2变为str1的第一个字符情况
        }
        for (int i = 1; i <= N; i++) {
            dp[i][0] = i; //由str1变为str2的第一个字符情况
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 当前字符相等的情况
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 左上角的值
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[N][M];
    }

    // dp空间压缩
    public static int getMinimumEditDistance2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int N = str1.length();
        int M = str2.length();
        if (N >= M) { // str1更长一点，以他作为行
            int[] dp = new int[M + 1];
            dp[0] = 0;
            for (int i = 1; i <= M; i++) {
                dp[i] = i;
            }
            // 填写普遍位置
            for (int i = 1; i <= N; i++) {
                int leftUp = dp[0]; //左上角的值
                dp[0] += 1; //往下更新
                int left = dp[0]; //左边的值
                for (int j = 1; j <= M; j++) {
                    int up = dp[j];
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[j] = leftUp; //直接拿左上角的值即可
                    } else {
                        // 左边 左上角 上边，三者取最小值
                        dp[j] = Math.min(left, Math.min(leftUp, up)) + 1;
                    }
                    left = dp[j]; // 更新左边
                    leftUp = up; // 更新左上角
                }
            }
            return dp[M];
        }
        // str1 的长度更短一点，以str2作为行
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        // 填写普遍位置
        for (int j = 1; j <= M; j++) {
            int leftUp = dp[0];
            dp[0] += 1;
            int left = dp[0];
            for (int i = 1; i <= N; i++) {
                int up = dp[i];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i] = leftUp;
                } else {
                    dp[i] = Math.min(left, Math.min(leftUp, up)) + 1;
                }
                left = dp[i]; // 更新左边的值
                leftUp = up; // 更新左上角的值
            }
        }
        return dp[N];
    }

}
