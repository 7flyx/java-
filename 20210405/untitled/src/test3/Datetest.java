package test3;

import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class Datetest {

    //测试data类
    public static void main(String[] args) {

        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        //将这个月的第一天赋值给date
        date = date.minusDays(today - 1);
        DayOfWeek weekday = date.getDayOfWeek(); //得到是星期几
        int value = weekday.getValue();  //得到是星期几，就返回几

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < value; i++) {
            System.out.print("   ");  //打印空格，填充前面的空白
        }

        while (month == date.getMonthValue()) {
            System.out.printf("%3d",date.getDayOfMonth());
            if (today == date.getDayOfMonth()){
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
           date = date.plusDays(1);  //向后推一天   date.minusDays(n),向前推n天
            if(date.getDayOfWeek().getValue() == 1) {
                //星期一，换行
                System.out.println();
            }
        }
        if(date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}
