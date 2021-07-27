package demo1;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        //反转字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String s = sc.nextLine();

        System.out.println("s:" + reverse(s));
    }

    public static String reverse(String s) {
        String line = "";

        for(int i =s.length() - 1; i >= 0 ;i--) {
            line += s.charAt(i);
        }

        return line;
    }

}
