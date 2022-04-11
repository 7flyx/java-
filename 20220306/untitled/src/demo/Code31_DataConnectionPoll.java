package demo;

import com.sun.javafx.image.IntPixelGetter;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 9:58
 * Description: 4月11号 第一个代码题 数据连接池
 */
public class Code31_DataConnectionPoll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine());
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLine();
            }

            System.out.println(calcMaxNumOfConnection(arr));
        }
    }

    private static int calcMaxNumOfConnection(String[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].indexOf("dis") != -1) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(1);
                res = Math.max(res, stack.size());
            }
        }
        return res;
    }
}
