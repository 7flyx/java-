package demo;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-15
 * Time: 10:55
 * Description:
 */
public class Code38_FiveBoard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] board = new String[20]; // 棋盘
            for (int i = 0; i < 20; i++) {
                board[i] = sc.nextLine();
            }
            System.out.println(isFiveNum(board) ? "Yes" : "No");
        }
    }

    public static boolean isFiveNum(String[] board) {
        if (board == null) {
            return false;
        }
        boolean res = false;
        boolean[][] visit = new boolean[20][20];
        for (int i = 0; i < 20 && !res; i++) {
            for (int j = 0; j < 20 && !res; j++) {
                char ch = board[i].charAt(j);
                if (ch == '*' || ch == '+') {
                    res = dfs(board, visit, i, j, ch, "LR", 0) >= 5 ||
                            dfs(board, visit, i, j, ch, "UD", 0) >= 5 ||
                            dfs(board, visit, i, j, ch, "LU_RD", 0) >= 5 ||
                            dfs(board, visit, i, j, ch, "LD_RU", 0) >= 5;
                }
            }
        }
        return res;
    }

    public static int dfs(String[] board, boolean[][] visit, int i, int j,
                          char ch, String direct, int count) {
        if (i < 0 || j < 0 || i == 20 || j == 20 || count == 5 ||
                board[i].charAt(j) == '.' || visit[i][j] || board[i].charAt(j) != ch) {
            return 0;
        }
        visit[i][j] = true;
        int res = 1;
        if (direct.equals("LR")) { // 左右方向
            res += dfs(board, visit, i, j + 1, ch, direct, count + 1); // 只需向右，本身遍历就是从左到右的
        } else if (direct.equals("UD")) { // 上下
            res += dfs(board, visit, i + 1, j, ch, direct, count + 1); // 只需往下走即可，遍历的时候是从上往下走的
        } else if (direct.equals("LU_RD")) { // 左上到右下
            res += dfs(board, visit, i + 1, j + 1, ch, direct, count + 1); // 只需往右下走
        } else if (direct.equals("LD_RU")) { // 左下到右上
            res += dfs(board, visit, i - 1, j + 1, ch, direct, count + 1);
        }
        visit[i][j] = false; // 恢复现场
        return res;
    }
}
