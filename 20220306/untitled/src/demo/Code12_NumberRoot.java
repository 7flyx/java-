package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-21
 * Time: 9:53
 * Description: 3 月 21号 第二个代码题  数根
 */
public class Code12_NumberRoot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String n = sc.nextLine();
            System.out.println(getNumberRoot(n));
        }
    }

    public static int getNumberRoot(String n) {
        if (n.length() == 1) { // 不符合条件的时候
            return Integer.parseInt(n);
        }

        while (n.length() != 1) {
            long res = 0;
            int len = n.length();
            for (int i = 0; i < len; i++) {
                int tmp = n.charAt(i) - '0';
                res += tmp;
            }
            n = res + "";
        }
        return Integer.parseInt(n);
    }

}
