package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-16
 * Time: 10:10
 * Description: 3月16号 强训题 第一个代码题 洗牌
 */
public class Code06_WashCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 次数
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt(); //牌的张数n
            int k = sc.nextInt();// 洗k次牌
            int[] arr = new int[num << 1];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = sc.nextInt();
            }

            StringBuilder sb = new StringBuilder();
            int[] res = getWashCard(arr, k);
            if (res == null) {
                for (int j = 0; j < arr.length; j++) {
                    sb.append(j + 1 < arr.length? arr[j] + " " : arr[j] );
                }
            } else {
                for (int j = res.length - 1; j >= 0; j--) {
                    sb.append(j == 0? res[j] : res[j] + " ");
                }
            }

            System.out.println(sb.toString());
        }
    }

    public static int[] getWashCard(int[] arr, int k) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int[] tmp = new int[arr.length];
        int i = 0;
        for (; i < k; i++) { //结束时，奇数返回tmp，偶数返回arr
            int left = arr.length / 2 - 1; // 左手的最后一张牌
            int right = arr.length - 1; // 右手的最后一张牌
            for (int j = 0; j < arr.length; j++) {
                if ((i & 1) == 1) { //奇数时，tmp-》arr
                    arr[j] = ((j & 1) == 1) ? tmp[left--] : tmp[right--]; //奇数放左手，偶数放右手
                } else { //偶数时，arr-》tmp
                    tmp[j] = ((j & 1) == 1) ? arr[left--] : arr[right--];
                }
            }
        }
        return (i & 1) == 1 ? tmp : null;
    }

}
