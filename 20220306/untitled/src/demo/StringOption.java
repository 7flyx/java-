package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-06
 * Time: 14:10
 * Description:
 */
public class StringOption {
    // 获取字符串前n个字符
    public static String left(String s, String flag) {
        String[] str =  s.split(flag);
        return str[0];
    }
}
