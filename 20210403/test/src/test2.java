

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {

        //静态建立数组，输入一个数值，判断数值中是否有该数组
        int[] arr = {11, 22, 33, 44, 55};

        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        System.out.println(Find(arr, number));
    }

    public static int Find(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }
}
