package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-17
 * Time: 16:07
 * Description: 3月17号 第一个代码题 小易的升级之路
 */
public class Code08_Upgrade {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 100;
        System.out.println((char)n);

//        while (sc.hasNext()) {
//            int N = sc.nextInt(); //野怪数量
//            int c = sc.nextInt(); //人的初始能力
//            int[] arr = new int[N];
//            for (int i = 0; i < N; i++) {
//                arr[i] = sc.nextInt();
//            }
//
//            System.out.println(getLastPower(c, arr));
//        }
    }

    public static int getLastPower(int c, int[] arr) {
        if (arr == null || arr.length == 0) {
            return c;
        }

        int res = c;
        for (int i = 0; i < arr.length; i++) {
            if (res >= arr[i]) {
                res += arr[i];
            } else { // 计算二者之间的约数
                res += getApproximateNum(arr[i], res);
            }
        }
        return res;
    }

    public static int getApproximateNum(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getApproximateNum(b, a % b);
    }

}
