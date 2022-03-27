package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-27
 * Time: 19:38
 * Description: 3月27日 第一个代码题 美国节日
 */
public class Code19_GetYearFestival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(getYearFestival(2014));
//        while (sc.hasNext()) {
//            int year = sc.nextInt();
//            System.out.println(getYearFestival(2014));
//        }
    }

    public static String getYearFestival(int year) {
        if (year < 2000 || year > 9999) {
            return "";
        }

        int flag = countNewYearDay(year); // 拿到元旦节是星期几
        System.out.println(flag);
        StringBuilder sb = new StringBuilder();
        sb.append(year + "-01-01" + "\n"); // 元旦，固定的
        // 马丁路德纪念日
        if (flag == 1) {
            sb.append(year + "-01" + "-15" + "\n");
        } else {
            sb.append(year + "-01-" + (15 + 7 - flag + 1) + "\n");
        }
        flag = (flag + 30) % 7 + 1; // 更新下一个1号是星期几

        // 总统节
        if (flag == 1) {
            sb.append(year + "-02" + "-15" + "\n");
        } else {
            sb.append(year + "-02-" + (15 + 7 - flag + 1) + "\n");
        }
        flag = flag + 27 + 31 + 30 + 31 - 1; // 2月 3月 4月 5月
        flag += (isPing(year) ? 0 : 1); // 闰年再多加一天
        flag = flag % 7 + 1;

        // 将士纪念日，上面是直接计算到6月1号，倒着减即可
        if (flag == 1) {
            sb.append(year + "-05-" + 25 + "\n");
        } else {
            sb.append(year + "-05-" + (31 - flag + 2) + "\n");
        }
        // 美国国庆节
        sb.append(year + "-07-" + "04" + "\n");
        flag += 30 + 31 + 31 - 1; // 6月 7月 8月
        flag = flag % 7 + 1;

        System.out.println(flag);
        // 劳动节 ---此时就是9月1号的星期几
        if (flag == 1) {
            sb.append(year + "-09-" + "01" + "\n");
        } else {
            sb.append(year + "-09-0" + (7 - flag + 2) + "\n");
        }
        flag = (flag + 30 + 31 - 1) % 7 + 1; // 9月 10月

        // 感恩节
        sb.append(year + "-11-" + (flag + 23) + "\n");

        sb.append(year + "-12-25");
        return sb.toString();
    }

    // 返回元旦节是星期几
    public static int countNewYearDay(int year) {
        if (year == 2000) {
            return 6; // 2000年的1月1号，星期六
        }
        // int flag = 6;// 2000年的1月1号，星期六
        int count = 365 * (year - 2000); // 先计算平年的天数
        // 计算year~2000年之间，闰年的个数，因为多一天
        count += (year - 1 - 2000) / 4 + 1;
        int number = count % 7; // 星期几
        return (5 + number) % 7 + 1;
    }

    public static boolean isPing(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }
}
