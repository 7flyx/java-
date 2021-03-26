package com.itheima;

/*
*   psvm：快速生成main函数
*   sout：快速生成输出函数println
*   行注释：Ctrl+ /
*   块注释：Ctrl+shift+/
*
*   自动缩进：Ctrl+shift+L
*   内容辅助键：Ctrl+shift+空格
*
* */

public class HelloWorld {
    public static void main(String[] args) {
        //System.out.println("hello world");

        //创建数组
        int[] arr = new int[5];  //与C语言的创建方式略有不同---默认初始化的值是0，new返回的还是首元素的地址

        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);


    }
}
