package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-30
 * Time: 14:22
 * Description:
 */

import java.util.*;

public class Code24_Fibonaicc {

    private static int[] days = new int[80];
    private static int index = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        days[0] = 1;
        days[1] = 1;
        while (sc.hasNext()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            System.out.println(calcSumOfBreakfast(from, to));
        }
    }

    public static int calcSumOfBreakfast(int from, int to) {
        if (from > to) {
            return 0;
        }
        if (from == to && to < index) {
            return days[to - 1];
        }

        int res = 0;
        // 从from与index，谁最小，就从谁开始
        for (int i = Math.min(from - 1, index); i < to; i++) {
            if (i >= index){
                days[i] = days[i - 1] + days[i - 2];
            }
            if (i >= from - 1) {
                res += days[i];
            }
        }
        index = Math.max(to, index);
        return res;
    }

}
