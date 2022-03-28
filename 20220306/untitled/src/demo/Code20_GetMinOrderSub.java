package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-28
 * Time: 10:41
 * Description:
 */


import java.util.Scanner;


//校招模拟：排序子序列

public class Code20_GetMinOrderSub {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }
        int flag = 0; // -1表示降序 ，0 表示相等， 1表示升序
        int result = 1; // base case，至少有一个结果
        for (int i = 1; i < n; i++) {
            if (data[i] > data[i - 1]) { // 当前情况是升序
                if (flag == 0) {
                    flag = 1;
                }
                if (flag == -1) { // 原先是降序，当前是升序，说明是波谷的情况，res++
                    flag = 0;
                    result++;
                }
            } else if (data[i] < data[i - 1]) { // 当前情况是降序
                if (flag == 0) {
                    flag = -1;
                }
                if (flag == 1) { // 原先是升序，当前是降序，说明是波峰的情况，res++
                    flag = 0;
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}


