package demo1;

import java.util.Scanner;

public class countCharSum {
    public static void main(String[] args) {
        //输入一个字符串，得到每个字符是大小写字母还是数字，并相应的数值加1
        int bigCh = 0;
        int littleCh = 0;
        int num = 0;
        Scanner sc = new Scanner(System.in);
        System.out.printf("请输入一个字符串:");
        String s = sc.nextLine();

        for (int i = 0; i < s.length(); i++) {  //此处的length是有圆括号的，应该是字符串内部的方法
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                littleCh ++;
            } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                bigCh ++;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num ++;
            }
        }

        System.out.println("bigCh:" + bigCh + "\tlittleCh:" + littleCh + "\tnumber:" + num);

    }
}
