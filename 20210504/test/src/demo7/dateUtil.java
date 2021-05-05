package demo7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtil {

    //私有的构造方法以及静态的成员方法
    private dateUtil() {
    }

    //成员方法
    public static String dateToString(Date date, String format) {
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }

    public static Date stringToDate(String date,String format) throws ParseException {
        //解析字符串
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(date);
        return d;
    }
}
