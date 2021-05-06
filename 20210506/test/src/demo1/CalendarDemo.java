package demo1;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarDemo {
    public static void main(String[] args) {
        //键盘录入任意年份，计算这一年二月份有多少天
        //用之前的思路就是，判断是平年还是闰年，现在不用了，直接用类，设置为3月1号，倒减一天即可
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份:");
        int year = sc.nextInt();

        Calendar c = Calendar.getInstance(); //不能之间创建他的对象
        c.set(year,2,1);  //记住的是，这里的月份是从0开始的，所以3就是这里的2
        c.add(Calendar.DAY_OF_MONTH,-1);

        System.out.println(c.get(Calendar.YEAR) + "年的2月有" + c.get(Calendar.DAY_OF_MONTH) + "天");
    }
}
