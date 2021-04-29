package demo1;

import java.util.Scanner;

public class stringbuilderDemo2 {
    public static void main(String[] args) {
        //StringBuilder 与String 之间的相互转换

//        String s = new String("hello");
//        StringBuilder s1 = new StringBuilder(s);  //转换为StringBuilder
//        System.out.println(s1);
//
//        String s2 = s1.toString();
//        System.out.println(s2);


        //拼接字符串
//        int[] arr = {1, 2, 3};
//        String s = arrayTostring(arr);
//        System.out.println("s:" + s);

        //键盘录入String类型的数据，调用方法进行反转
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println("s:" + myReverse(s));

    }

    public static String myReverse(String s) {
        //通过StringBuilder类进行反转
//        StringBuilder sb = new StringBuilder(s);
//        sb.reverse();
//        return sb.toString();

        return new StringBuilder(s).reverse().toString(); //直接new出来一个匿名对象，进行一系列的操作后，直接返回的
    }

    public static String arrayTostring(int[] arr) {
        //用StringBuilder 对象进行字符串的拼接操作
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
