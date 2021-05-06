package demo2;

import java.util.Scanner;

public class ExceptionDemo3 {
    public static void main(String[] args) {
        //自定义异常
        //例如在输入成绩时，应在0-100之间，如果不是的话，抛出异常，最好是在编译时抛出，即就是编译时异常
        //构造异常类，继承在编译时异常类Exception下
        TercherDemo t1 = new TercherDemo();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入分数:");
        int sorce = sc.nextInt();
        try {
            t1.CheckSorce(sorce);  //调用方法抛出异常，使用try catch 结构
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
