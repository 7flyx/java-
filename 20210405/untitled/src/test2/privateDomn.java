package test2;

import java.util.Scanner;
public class privateDomn {
    //封装
    public static void main(String[] args) {
        Private p = new Private();

        //进行赋值
        p.name = "飞人";
        System.out.print("请输入年龄:");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        p.setAge(number);  //private修饰，只能以这种方式进行赋值运算

        System.out.println(p.name + "," + p.getAge());
    }
}
