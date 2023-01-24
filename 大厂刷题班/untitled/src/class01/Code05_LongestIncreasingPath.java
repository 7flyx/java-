package class01;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:13
 * Description: 给定一个二维数组matrix，你可以从任何位置出发，走向上、下、左、右四个方向，返回能走出来的最长的递增链长度
 */
public class Code05_LongestIncreasingPath {
    public static void main(String[] args) {

    }

    // DFS暴力解
    public static int longestIncreasingLength(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, process(matrix, i, j));
            }
        }
        return ans;
    }

    private static int process(int[][] matrix, int i, int j) {
        int len = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) { // 上
            len = Math.max(len, process(matrix, i - 1, j));
        }
        if (i + 1 < matrix.length && matrix[ i + 1][j] > matrix[i][j]) { // 下
            len = Math.max(len, process(matrix, i + 1, j));
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) { // 左
            len = Math.max(len, process(matrix, i, j - 1));
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) { // 右
            len = Math.max(len, process(matrix, i, j + 1));
        }
        return len + 1;
    }

    // 因为matrix表中的开始后，计算出来的结果对下一次的计算有用。所以直接使用dp表进行存储即可
    public static int longestIncreasingLength1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int ans = 0;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    public static int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]? process(matrix, i - 1, j, dp) : 0;
        int down = i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]? process(matrix, i + 1, j, dp) : 0;
        int left = j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]? process(matrix, i, j - 1, dp) : 0;
        int right = j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]? process(matrix, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }
}
