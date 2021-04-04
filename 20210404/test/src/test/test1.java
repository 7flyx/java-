package test;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {

        //键盘录入学生成绩，去掉最高分与最低分，算出平均分
        int[] arr = new int[6];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的信息:");
            arr[i] = sc.nextInt();
        }
        System.out.println("平均成绩为" +  getSum(arr));
    }


    public static int getSum(int[] arr) {
        int max = 0;
        int min = 100;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
            if(min > arr[i])
                min = arr[i];
            sum += arr[i];
        }
        return (sum - max - min) / (arr.length - 2);
    }
}
