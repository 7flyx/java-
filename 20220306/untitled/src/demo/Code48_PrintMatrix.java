package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-24
 * Time: 12:12
 * Description: 转圈打印矩阵
 */
import java.util.*;
public class Code48_PrintMatrix {

    public static void main(String[] args) {
        Printer p = new Printer();
//        int[][] arr = {{1,2}, {3, 4}};
        int[][] arr = {{1,2},{3,4},{5,6},{7,8},{9,10}};
//        int[] res = p.clockwisePrint(arr, 1, 1);
//        System.out.println(Arrays.toString(res));
    }



    public static class Printer {
        private int index;
        public int[] clockwisePrint(int[][] mat, int n, int m) {
            if (mat == null) {
                return new int[0];
            }

            int[] res = new int[n * m];
            index = 0;
            int r1 = 0;
            int c1 = 0; // 左上角顶点
            int r2 = n - 1;
            int c2 = m - 1; // 右下角顶点
            while (r1 <= r2 && c1 <= c2) {
                print(mat, res, r1, c1, r2, c2);
                // 更新左上角和右上角
                r1++;
                c1++;
                r2--;
                c2--;
            }
            return res;
        }

        private void print(int[][] mat, int[] res, int r1, int c1, int r2, int c2) {
            if (r1 == r2) { // 只有一行的时候
                for (int i = c1; i <= c2; i++) {
                    res[index++] = mat[r1][i];
                }
                return;
            }
            if (c1 == c2) { // 只有一列的时候
                for (int i = r1; i <= r2; i++) {
                    res[index++] = mat[i][c1];
                }
                return;
            }

            int row = r1;
            int col = c1;
            // 不止一行和一列
            while (col <= c2) { // 上面一行
                res[index++] = mat[row][col];
                if (col + 1 > c2) {
                    row++; // 提前进入下一行
                    break;
                }
                col++;
            }

            while (row <= r2) { // 最右一列
                res[index++] = mat[row][col];
                if (row + 1 > r2) {
                    col--; // 提前往左走一个单位，顶点已经打印了
                    break;
                }
                row++;
            }

            while (col >= c1) { // 下面一列
                res[index++] = mat[row][col];
                if (col - 1 < c1) {
                    row--; // 提前往上走一个单位，顶点已经打印了
                    break;
                }
                col--;
            }

            while (row > r1) { // 最左一列
                res[index++] = mat[row][col];
                if (row - 1 < r1) {
                    break;
                }
                row--;
            }
        }
    }
}
