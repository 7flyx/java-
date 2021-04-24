package demo1;

import java.util.function.DoubleToIntFunction;

public class StringDemo2 {
    public static void main(String[] args) {
        //字符串的比较
        //==      在基本数据类型时，比较的是数值，，，，，在引用数据类型时，比较的是内存地址

        char[] cha = {'a','b','c'};
        String s1 = new String(cha);
        String s2 = new String(cha);

        String s3 = "abc";
        String s4 = "abc";
        //引用数据类型，比较的是内存地址
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);

        System.out.println("----------------");
        //判断引用数据类型里面的数据，需要用到方法 例如 ： s1.equals(s2)
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s3.equals(s4));
    }
}
