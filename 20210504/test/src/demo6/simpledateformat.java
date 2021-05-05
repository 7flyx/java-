package demo6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class simpledateformat {
    public static void main(String[] args) throws ParseException {
        // 格式化时间--Date -> String
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();  //默认格式--21-5-4 下午4:26
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //自定义模式  2021年05月04日 16:29:04
        String s = sdf.format(d1);
        String s2 = sdf2.format(d1);
        System.out.println(s);
        System.out.println(s2);

        //解析时间--输入一个时间，将它解析为Date类型的输出
        //String -> Date
        String s3 = "2021年3月3日 19:20:20";
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date d2 = sdf3.parse(s3);
        System.out.println(d2); //Wed Mar 03 19:20:20 CST 2021
    }
}
