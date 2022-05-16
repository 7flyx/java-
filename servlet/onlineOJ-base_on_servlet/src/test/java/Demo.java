class Test2 {

}


public class Demo {
    public static void main(String[] args) {
        String[] arr = {"10", "0001", "111001", "1", "0"};
        String[] arr1 = {"10", "0", "1"};
        int m = 1;
        int n = 1;
        System.out.println(findMaxForm3(arr1, m, n));
    }

    // 经典dp
    public static int findMaxForm2(String[] strs, int m, int n) {
        if (strs == null || m < 0 || n < 0) {
            return 0;
        }

        int len = strs.length;
        // 可变参数是 len、m、n，所以建三维表。并且这三个参数的在递归中的变化范围是
        // 0 ~ len， 0 ~ m， 0 ~ n
        int[][][] dp = new int[m + 1][n + 1][len + 1];

        // 首先统计出每个字符串0和1的个数
        int[] countOne = new int[len];
        for (int i = 0; i < len; i++) {
            countOne[i] = countOne(strs[i]);
        }

        for (int k = len - 1; k >= 0; k--) {
            int oneSum = countOne[k];
            int zeroSum = strs[k].length() - oneSum;
            for (int i = 0; i <= m; i++) { // 指向0
                for (int j = 0; j <= n; j++) { // 指向1
                    dp[i][j][k] = dp[i][j][k + 1]; // 不要当前物品的情况
                    // 要当前物品的情况
                    if (i + zeroSum <= m && j + oneSum <= n) {
                        int tmp = 1 + dp[i + zeroSum][j + oneSum][k + 1];
                        dp[i][j][k] = Math.max(dp[i][j][k], tmp);
                    }
                }
            }
        }

        return dp[0][0][0];
    }

    // dp压缩
    public static int findMaxForm3(String[] strs, int m, int n) {
        if (strs == null || m < 0 || n < 0) {
            return 0;
        }

        int len = strs.length;
        // 可变参数是 len、m、n，所以建三维表。并且这三个参数的在递归中的变化范围是
        // 0 ~ len， 0 ~ m， 0 ~ n
        int[][] dp = new int[m + 1][n + 1];

        // 首先统计出每个字符串0和1的个数
        int[] countOne = new int[len];
        for (int i = 0; i < len; i++) {
            countOne[i] = countOne(strs[i]);
        }

        for (int k = len - 1; k >= 0; k--) {
            int oneSum = countOne[k];
            int zeroSum = strs[k].length() - oneSum;
            for (int i = 0; i <= m; i++) { // 指向0
                for (int j = 0; j <= n; j++) { // 指向1
                    // dp[i][j][k] = dp[i][j][k + 1]; // 不要当前物品的情况
                    // 要当前物品的情况
                    if (i + zeroSum <= m && j + oneSum <= n) {
                        int tmp = 1 + dp[i + zeroSum][j + oneSum];
                        dp[i][j] = Math.max(dp[i][j], tmp);
                    }
                }
            }
        }

        return dp[0][0];
    }


    private static int countOne(String str) {
        int n = str.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}


