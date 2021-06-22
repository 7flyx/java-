package demo1;

public class Matrix {
    public static void main(String[] args) {
        //矩阵处理技巧
        /*
            1. zigzag打印矩阵
            2. 转圈打印矩阵---顺时针
            3. 原地旋转正方形矩阵---整体向右下旋转90度
            核心技巧： 找到coding上的宏观调度
         */


    }

    public static void zigzag(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        //斜着打印矩阵的数据
        //定义两个点A，B，分别横着走和竖着走，每走一步，就打印斜着对应的数据
        int Ar = 0, Ac = 0;
        int Br = 0, Bc = 0;
        int endR = matrix.length - 1; //行数
        int endC = matrix[0].length - 1; //列数

        boolean fromUp = false; //从右上角打印
        while (Ar != endR + 1) {
            printLevel(matrix, Ar, Ac, Br, Bc, fromUp);
            Ar = Ac == endC ? Ar + 1 : Ar;
            Ac = Ac == endC ? Ac : Ac + 1;

            Bc = Br == endR ? Bc + 1 : Bc;
            Br = Br == endR ? Br : Br + 1;  //特别注意这四个条件的加减，还得注意先后顺序，这些边界情况要考虑清楚
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printLevel(int[][] matrix, int Ar, int Ac, int Br, int Bc, boolean fromup) {
        if (fromup) { //右上到左下打印
            while (Ar != Br + 1) {
                System.out.println(matrix[Ar][Ac] + " ");
                Ar++;
                Ac--;
            }
        } else { //左下到右上打印
            while (Br != Ar + 1) {
                System.out.println(matrix[Br][Bc] + " ");
                Br--;
                Bc++;
            }
        }
    }

    public static void cycleMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int lR = 0, lC = 0; //左上角结点
        int rR = matrix.length - 1, rC = matrix[0].length - 1; //右下角
        while (lR <= rR && lC <= rC) {
            printCycle(matrix, lR++, lC++, rR--, rC--); //循环一次，两个结点，分别往中心靠
        }

    }

    private static void printCycle(int[][] matrix, int lR, int lC, int rR, int rC) {
        if (lR == rR) { //只有一行
            while (lC <= rC) {
                System.out.print(matrix[lR][lC++] + " ");
            }
        } else if (lC == rC) { //只有一列
            while (lR <= rR) {
                System.out.print(matrix[lR++][lC] + " ");
            }
        } else {
            int x = lR;
            int y = lC; //临时保存左上角的左边
            while (lC < rC) { //第一行
                System.out.print(matrix[lR][lC++] + " ");
            }
            while (lR < rR) {
                System.out.print(matrix[lR++][lC] + " ");
            }
            while (rC > y) {
                System.out.print(matrix[rR][rC--] + " ");
            }
            while (rR > x) {
                System.out.print(matrix[rR--][rC] + " ");
            }
        }

    }

    public static void rotateMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        //还是定义左上角和右下角，只是旋转，外围的还是在外围
        int a = 0, b = 0;
        int c = matrix.length - 1, d = matrix[0].length - 1; //右下角的坐标
        while (a < c) {
            rotate(matrix, a, b, c, d);
        }
    }

    private static void rotate(int[][] matrix, int a, int b, int c, int d) {
        for (int i = 0; i < d - b; i++) {
            int tmp = matrix[a][b+i];
            matrix[a][b+i] = matrix[c+i][b];
            matrix[c+i][b] = matrix[c][d-i];
            matrix[c][d-i] = matrix[a+i][d];
            matrix[a+i][d] = tmp; //逆时针方向，一个个拷贝
        }
    }
}
