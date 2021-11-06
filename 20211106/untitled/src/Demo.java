/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-06
 * Time: 20:08
 * Description:
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Demo {
}


class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        //同样的数组，代表当前点是否被访问过
        boolean[][] isVisit = new boolean[grid.length][grid[0].length];
        return process(grid, isVisit, 0, 0);

    }

    private int process(int[][] grid,boolean[][] isVisit, int row, int col) {
        if (row == grid.length || col == grid[0].length || isVisit[row][col]) {
            return 0;
        }
        int res = 0;
        isVisit[row][col] = true;
        if (grid[row][col] == 1) {
            if (row - 1 < 0 || grid[row - 1][col] == 0) { //向上
                res += 1;
            }
            if (row + 1 >= grid.length || grid[row + 1][col] == 0) { //向下
                res += 1;
            }
            if (col - 1 < 0 || grid[row][col - 1] == 0) { //向左
                res += 1;
            }
            if (col + 1 >= grid[0].length || grid[row][col + 1] == 0) { //向右
                res += 1;
            }
        }

        res += process(grid, isVisit, row, col + 1); //向右走
        res += process(grid, isVisit, row + 1, col); //向下走
        return res;
    }
}