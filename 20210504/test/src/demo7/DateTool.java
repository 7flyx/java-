package demo7;

import java.text.ParseException;
import java.util.Date;

public class DateTool {
    public static void main(String[] args) throws ParseException {
        //日期工具类测试
        Date d = new Date();
        String s = dateUtil.dateToString(d,"yyyy-MM-dd HH:mm:ss");  //格式化
        System.out.println(s);

        String s2 = "2021-02-02 5:20:00";
        Date d2 = dateUtil.stringToDate(s2,"yyyy-MM-dd HH:mm:ss"); // 解析
        System.out.println(d2);
    }
}
