package demo;
import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-28
 * Time: 22:33
 * Description: 3月29号 第二个代码题 斐波那契凤尾
 */
public class Code22_FibonacciTail {
        private static int[] record = new int[10_0000];
        private static int index = 2; // 指向record数组
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            record[0] = 1;
            record[1] = 2;
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split("<br/>");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    int n = Integer.parseInt(arr[0]);
                    System.out.printf((n < 29 ? "%d" : "%06d"),
                            getFibonacciNum(n));
                    if (i + 1 < arr.length) {
                        System.out.print("<br/>");
                    }
                    System.out.println();
                }
            }
        }

        public static int getFibonacciNum(int n) {
            if (n < 0) {
                return -1; // 非法数据
            }
            if (index >= n) {
                return record[n - 1];
            }

            int num1 = record[index - 2];
            int num2 = record[index - 1];
            int i = index;
            for (; i < n; i++) {
                record[i] = (num1 + num2) % 100_0000;
                num1 = num2;
                num2 = record[i];
            }
            index = i;
            return record[n - 1];
        }

}
