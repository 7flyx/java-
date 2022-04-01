package demo;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-01
 * Time: 14:44
 * Description:
 */




import java.util.*;

class Main {
    public static void f() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            long sum = 1;
            for (int i = 2; i <= n; i++) {
                sum *= i;
            }
            System.out.printf("%.2f",getNoAward(n) * 100.0 / sum);
            System.out.println("%");
        }
    }

    public static double getNoAward(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return (n - 1) * (getNoAward(n - 2) + getNoAward(n - 1));
    }
}


public class Code27_Award {

    public static void main(String[] args) {
        Main.f();
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            double sum1 = factorial(n);
            double sum2 = count(n);
            double result = (sum2 / sum1) * 100; //计算成%的形式
            System.out.println(String.format("%.2f", result) + "%");
        }
    }

    //计算所有人都抽不到奖的情况：错排算法
    private static double count(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return (n - 1) * (count(n - 1) + count(n - 2));
        }
    }

    //计算阶乘：迭代写法
    private static double factorial(int n) {
        double sum = 1;
        while (n > 1) {
            sum = sum * n;
            n--;
        }
        return sum;
    }

    //计算阶乘：递归写法
    private static double factorical2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorical2(n - 1);
    }


}
