package demo7;

import java.util.Calendar;

public class calendarDemo {
    public static void main(String[] args) {

        Calendar c = Calendar.getInstance();  //本身是抽象类，需要子类进行创建对象
        c.add(Calendar.YEAR,-1);  //在它本身的基础上，年份添加  -1
        int year = c.get(Calendar.YEAR);  //得到年份
        int month = c.get(Calendar.MONTH) + 1; //得到月份--这个方法得到的月份范围是0-11，所以需要加1
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "," + month + "," +day);
    }
}
