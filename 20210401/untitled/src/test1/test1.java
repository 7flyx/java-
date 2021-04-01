package test1;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {

        //导包：1. 手动导包  import java.util.Scanner;
        //  2. 自动导包  点击函数后，Alt+enter
        //
        //输入数据，选择相应的活动
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入星期几:");
        int num = sc.nextInt();

        if (num < 1 || num > 7) {
            System.out.println("输入有误");
        }

        if (num == 1) {
            System.out.println("爬山");
        } else if (num == 2) {
            System.out.println("吃火锅");
        } else if (num == 3) {
            System.out.println("打游戏");
        } else if (num == 4) {
            System.out.println("干饭");
        } else if (num == 5) {
            System.out.println("逛街");
        } else if (num == 6) {
            System.out.println("约会");
        } else {
            System.out.println("睡觉");
        }
    }
}
