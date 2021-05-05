package demo6;

import java.util.Date;

public class demo6 {
    public static void main(String[] args) {
        //日期类 Date

        Date d1 = new Date();  //注意是util包
        System.out.println(d1); //得到的是当前计算机的系统时间


        //long time = 1000 * 60 * 60; //单位是毫秒
        long time = System.currentTimeMillis(); //拿到的也是这个点到70年的时间差，单位毫秒
        Date d2 = new Date(time);
        System.out.println(d2); //Thu Jan 01 09:00:00 CST 1970 ,time就是一个小时，距离时间戳 70年1月1日，0点0分0秒后加上了一个小时
                                //和中国时区8个小时

    }

}
