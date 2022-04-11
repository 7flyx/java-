package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 21:21
 * Description: 4月11号 第二个代码题 蘑菇阵
 */
public class Code34_MushroomArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int row, col;
        double[][] board = new double[n + 1][m + 1];
        for (int i = 0; i < k; i++) {
            row = sc.nextInt();
            col = sc.nextInt();
            board[row][col] = -1;
        }

        System.out.printf("%.2f\n", calc(board, n, m));
    }

    // isUp为真，表示计算分子，也就是可以通往右下角的数量
    public static int dfs(boolean[][] board, int i, int j, boolean isUp) {
        if (i == board.length - 1 && j == board[0].length - 1) {
            return 1;
        }
        // 当前越界，或者是有蘑菇的情况，并且还是计算分子的情况，提前返回
        if (i == board.length || j == board[0].length || (board[i][j] && isUp)) {
            return 0;
        }
        int res = dfs(board, i + 1, j, isUp); // 下
        res += dfs(board, i, j + 1, isUp);
        return res;
    }

    // 每个点的概率= 上边的概率/2 + 左边的概率/2
    public static double calc(double[][] board, int n, int m) {
        board[1][1] = 1; // 左上角的值，概率就是1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] != -1) { // 不是蘑菇的情况
                    // 先计算上左边出发的情况
                    if (board[i - 1][j] != -1) {
                        board[i][j] += j == m ? board[i - 1][j] : board[i - 1][j] / 2;
                    }
                    // 再计算从左边出发的情况
                    if (board[i][j - 1] != -1) {
                        board[i][j] += i == n ? board[i][j - 1] : board[i][j - 1] / 2;
                    }
                }
            }
        }
        return board[n][m];
    }
}
