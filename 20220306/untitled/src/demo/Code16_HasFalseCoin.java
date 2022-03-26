package demo;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-25
 * Time: 14:43
 * Description: 3月25号 第二个代码题
 */
public class Code16_HasFalseCoin {


    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static int findCoin(int n) {
        if (n == 1)
            return 0;
        if (n <= 3)
            return 1;
        int metage, rest, times = 0;
        // 3等分前，先加2，使得metage的值尽量大于rest
        // (metage,metage,rest)
        metage = (n + 2) / 3;
        rest = n - 2 * metage;

        times = 1 + findCoin(Math.max(metage, rest));
        return times;
    }

    public static int findCoin2(int n) {
        if (n == 1) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }

        long count = 2;
        int res = 1;
        long cur = 3;
        while (n > cur) {
            count *= 3;
            cur += count;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
//        for (int i = 1; i < 50; i++) {
//            if (findCoin(i) != findCoin2(i)) {
//                System.out.println("错误");
//            }
//        }
        if (findCoin(1162261468) != findCoin2(1162261468)) {
            System.out.println("错误");
        }
    }



}
