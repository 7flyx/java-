/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-13
 * Time: 22:18
 * Description:剑指offer47 礼物的最大价值
 */
public class LeetCode47_MaxNumOfGift {
    class Solution {
        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int row = grid.length;
            int col = grid[0].length;
            int N = grid.length <= grid[0].length? row : col;

            int[] dp = new int[N];
            if (N == row) { //以最右的一列作为基础
                dp[0] = grid[0][0];
                for (int i = 1; i < N; i++) {
                    dp[i] = grid[i][0] + dp[i - 1];
                }
                for (int j = 1; j < col; j++) { //第二列
                    dp[0] += grid[0][j]; //第一行做累加
                    for (int i = 1; i < row; i++) { //第二行
                        dp[i] = Math.max(dp[i], dp[i - 1]) + grid[i][j];
                    }
                }
                return dp[N - 1]; //返回最后位置的值即可
            }

            //以最后一行作为基础
            dp[N - 1] = grid[row - 1][N - 1]; //从右向左更新
            for (int i = N - 2; i >= 0; i--) {
                dp[i] = grid[row - 1][i] + dp[i + 1];
            }
            for (int i = row - 2; i >= 0; i--) { //倒数第二行
                dp[N - 1] += grid[i][N - 1]; //累加最右一列
                for (int j = col - 2; j >= 0; j--) {
                    dp[j] = Math.max(dp[j], dp[j + 1]) + grid[i][j];
                }
            }
            return dp[0]; //返回第一个位置即可
        }

        public int maxValue1(int[][] grid) {
            if (grid == null) {
                return 0;
            }

            int[][] dp = new int[grid.length][grid[0].length];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < grid.length; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int j = 1; j < grid[0].length; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }

            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; j++) {
                    dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[grid.length - 1][grid[0].length - 1];
        }
    }
}
