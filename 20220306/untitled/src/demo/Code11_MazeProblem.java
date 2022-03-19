package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-19
 * Time: 16:12
 * Description: 3月19号 第二个代码题
 */
public class Code11_MazeProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] board = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        List<Integer> list = new ArrayList<>(); // 用于存储沿途的坐标
        StringBuilder sb = new StringBuilder();
        if (dfs(list, board, 0, 0)) {
            // 打印沿途的坐标
            for (int i = 0; i < list.size(); i += 2) {
                sb.append("(" + list.get(i) + "," + list.get(i + 1) + ")\n");
            }
        }
        System.out.println(sb.toString());

    }

    public static boolean dfs(List<Integer> list, int[][] board, int i, int j) {
        if (i == board.length - 1 && j == board[0].length - 1 && board[i][j] != 1) {
            list.add(i);
            list.add(j);
            return true;
        }

        if (i == board.length || j == board[0].length || i < 0 || j < 0 || board[i][j] == 1) {
            return false;
        }

        list.add(i);
        list.add(j);
        board[i][j] = 1;
        int index = list.size();
        // 向下
        if ( dfs(list, board, i + 1, j)) {
            return true;
        }
        // 向右
        if (dfs(list, board, i, j + 1)) {
            return true;
        }
        if (dfs(list, board, i - 1, j)) { // 向上
            return true;
        }
        if( dfs(list, board, i, j - 1)) { // 向左
            return true;
        }
        list.remove(index - 1);
        list.remove(index - 2);
        board[i][j] = 0;
        return false;
    }

}
