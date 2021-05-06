package demo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        //编译时异常与运行时异常
        method();

    }

    public static void method() {
        try {
            String s = "2020.02.03";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            sdf.parse(s);   //称为编译时异常，会提示你这个地方可能会出错  用try catch解决
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void method1() {
        int[] arr = {1, 2, 3};
        System.out.println(arr[3]); //运行时异常
    }
}
