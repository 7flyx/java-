package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-28
 * Time: 21:14
 * Description: 3月29日 第一个代码题
 */

import java.util.*;

public class Code21_TaoBaoCalcProfits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] times = sc.nextLine().split(" "); // 以空格进行分割
            System.out.println(calcProfits(times));
        }
    }

    public static int calcProfits(String[] times) {
        if (times == null || times.length != 6) {
            return 0;
        }

        int startYear = Integer.parseInt(times[0]);
        int startMonth = Integer.parseInt(times[1]);
        int startDay = Integer.parseInt(times[2]);
        int endYear = Integer.parseInt(times[3]);
        int endMonth = Integer.parseInt(times[4]);
        int endDay = Integer.parseInt(times[5]);
        // 某个月是否是素数的情况
        boolean[] flags = {false, true, true, false, true, false, true, false, false, false, true, false};
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 先计算有多少天，然后全部*2.最后再统计有多少素数月份，减去即可
        int res = 0; // endDay
        if (startYear != endYear || startMonth != endMonth) {
            int count = days[startMonth - 1] - startDay + 1; // 开始月的天数
            res += (flags[startMonth - 1] ? count : 2 * count); // 开始月的收入
            res += flags[endMonth - 1] ? endDay : 2 * endDay;
        } else { // 上诉两个都相等的情况下
            res += flags[endMonth - 1] ? (endDay - startDay + 1) : 2 * (endDay - startDay + 1);
            return res;
        }

        if (!isPing(startYear) && startMonth == 2) { // startYear 是闰年的情况下，2月份要多加一天
            res += 1;
        }
        if (!isPing(endYear) && endMonth == 2 && endDay == 28) { // endMonth 是闰年，且还有2月份的情况，多加一天
            res += 1;
        }

        // 计算开始月和结束月的那一年的情况。比如不是同一年之间的
        if (startYear != endYear) {
            for (int i = startMonth + 1; i <= 12; i++) {
                res += flags[i - 1] ? days[i - 1] : 2 * days[i - 1];
                if (i == 2 && !isPing(startYear)) { // 闰年的情况下，2月份要多加一天
                    res += 1;
                }
            }
            for (int i = 1; i < endMonth; i++) {
                res += flags[i - 1] ? days[i - 1] : 2 * days[i - 1];
                if (i == 2 && !isPing(endYear)) { // 闰年，且还有2月份的情况，多加一天
                    res += 1;
                }
            }
        } else { // 是同一年的情况
            for (int i = startMonth + 1; i < endMonth; i++) {
                res += flags[i - 1] ? days[i - 1] : 2 * days[i - 1];
                if (i == 2 && !isPing(i)) { // 闰年，且还有2月份的情况，多加一天
                    res += 1;
                }
            }
        }


        // 统计年之间的情况
        int sum = 0; // 计算平年，一整年的收入
        for (int i = 0; i < 12; i++) {
            sum += (flags[i] ? days[i] : 2 * days[i]);
        }
        for (int year = startYear + 1; year < endYear; year++) {
            // 平年sum， 闰年sum + 1。闰年要多2月一天的利润
            if (isPing(year)) {
                res += sum;
            } else {
                res += sum + 1;
            }
        }
        return res;
    }

    public static boolean isPing(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return false;
        }
        return true;
    }

}
