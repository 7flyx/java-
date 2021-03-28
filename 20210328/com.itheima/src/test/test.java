package test;


import com.sun.deploy.security.SelectableSecurityManager;

import java.sql.SQLOutput;public class test {


    public static void main(String[] args) {

//        int[] arr = new int[4];
//        System.out.println(arr);
//        System.out.println(arr[0]);
//        System.out.println(arr[1]);
//
//        arr[0] = 100;
//        arr[1] = 200;
//
//        System.out.println(arr[0]);
//        System.out.println(arr[1]);
//
//
//        //类似于C语言的指针，还可以声明一个数组，去指向上面的数组
//        int[] arr2 = arr;
//
//        arr2[0] = 111;
//        arr2[1] = 222;
//
//        System.out.println(arr);
//        System.out.println(arr2[0]);
//        System.out.println(arr2[1]);


        //arr.length用于计算数组的长度，类似于C语言中的strlen
//        int[] arr = {1,2 ,3 ,4 ,5};
//        int max = arr[0];
//        for(int i = 1;i < arr.length;i++) {
//            if(max < arr[i])
//                max = arr[i];
//
//        }
//        System.out.println("max:"+max);


        //方法的定义与调用---类似于C语言中的自定义函数--似乎并不需要先声明再使用
        //isEvenNumber();
        getMAX();

    }
//    public static void isEvenNumber () {
//        int num = 10;if(num%2 == 0){
//
//        System.out.println(true);
//        } else {
//            System.out.println(false);
//        }
//
//    }


    //用方法，去判断两个数的最大数
    public static void getMAX () {
        int a = 10;
        int b = 20;
        if(a > b)
            System.out.println("max:" + a);
        else
            System.out.println("max:" + b);
    }
}
