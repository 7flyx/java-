import java.util.Scanner;

/**
 * Created by flyx
 * Description: 杨辉三角
 * User: 听风
 * Date: 2021-09-14
 * Time: 10:28
 */

public class Code01_TriangleOfY {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] array = new int[n][n];
        printOfTriangle(array);
        for (int i = 0; i < n; i++) {
            int blankNum = n * 5 / (i + 2);
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k < blankNum; k++) {
                    System.out.print(" ");
                }
                System.out.printf("%d", array[i][j]);
            }
            System.out.println();
        }

    }

    public static void printOfTriangle(int[][] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            array[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                if (j == i) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }
            }

        }
    }
}
