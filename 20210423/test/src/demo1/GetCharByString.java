package demo1;

import java.util.Scanner;

public class GetCharByString {
    public static void main(String[] args) {
        //分别打印输出一个字符串的每个字符
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String s = sc.nextLine();
        //s.length()  可以返回一个字符串的长度
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));  //s.charAt(索引值)，可以得到一个字符串的字符，索引值是从0开始，与数组一样的
        }
    }
}
