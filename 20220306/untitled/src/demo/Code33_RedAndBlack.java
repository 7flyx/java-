package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 21:01
 * Description: 4月11号 第一个代码题 红与黑
 */
import java.util.*;

public class Code33_RedAndBlack {
    private static int row, col;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            row = sc.nextInt();
            col = sc.nextInt();
            String[] board = new String[row];
            int startR = 0;
            int startC = 0; // 起点的横纵坐标
            for (int i = 0; i < row; i++) {
                board[i] = sc.next();
                for (int j = 0; j < col; j++) {
                    if (board[i].charAt(j) == '@') {
                        startR = i;
                        startC = j;
                    }
                }
            }
            boolean[][] isVisit = new boolean[row][col];
            System.out.println(dfs(board, isVisit, startR, startC));
        }
    }

    public static int dfs(String[] board, boolean[][] isVisit, int i, int j) {
        if (i < 0 || j < 0 || i == row || j == col || board[i].charAt(j) == '#' || isVisit[i][j]) {
            return 0;
        }
        isVisit[i][j] = true;
        int res = 1;
        res += dfs(board, isVisit, i + 1, j); // 下
        res += dfs(board, isVisit, i - 1, j);  // 上
        res += dfs(board, isVisit, i, j + 1); // 右
        res += dfs(board, isVisit, i, j - 1); // 左
        return res;
    }


}
