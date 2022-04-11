package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-10
 * Time: 20:37
 * Description: https://ac.nowcoder.com/acm/contest/18839/1039
 */
public class Code30_MultiplyToMinSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.printf("%.3f\n", getMinSum(n));
    }

    private static double getMinSum(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 3;
        }
        double res = Double.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n / i; j++) {
                if (n % (i * j) == 0) {
                    res = Math.min(res, i + j + (n * 1.0 / i / j));
                }
            }
        }
        return res;
    }


}
