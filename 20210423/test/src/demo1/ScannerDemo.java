package demo1;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {

        //scanner，键盘录取字符串类型的数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串类型的数据:");
        String s = sc.nextLine();

        System.out.println("你输入的数据是" + s);
    }

}
