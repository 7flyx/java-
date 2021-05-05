package demo5;

import java.util.Arrays;

public class demo5 {
    public static void main(String[] args) {
        //将一个字符串数据，进行有序的排列，最后并以字符串的形式进行输出
        String s = "33 22 55 65 11 12 94";

        //需要进行排序，首先先转换为数组，在进行排序操作--字符串成员方法
        String[] strArray = s.split(" "); //会将字符串中的数据分开组成字符串数组

        int[] arr = new int[strArray.length];
        //字符串数组转换为整形数组
        for (int i = 0; i < strArray.length; i++) {
            arr[i] = Integer.parseInt(strArray[i]);
        }

        //排序操作
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(" ");
            }
        }

        String result = sb.toString();
        System.out.println("result: " + result);



        //装箱与拆箱的概念
        //包装类与对应的基本数据类型 ： 基本数据类型 -》 包装类 称为装箱。  反之称为拆箱.
        //还有自动装箱与拆箱
        int x = 100; //基本数据类型
        Integer i = Integer.valueOf(100);  //手动装箱,调用成员方法
        Integer ii = 100;  //自动装箱，但是底层还是调用的成员成员方法

        //还有就是在实际的开发中，自动装箱与拆箱，特别是引用数据类型时，需要判断是否为空指针null
        Integer iii = null;
        if (iii != null) {
            iii += 100; //自动拆箱与装箱，两步都做了
        }

    }
}
