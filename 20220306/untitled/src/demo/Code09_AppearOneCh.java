package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-17
 * Time: 16:17
 * Description: 3月17号 第二个代码题 只出现一次的字符
 */
public class Code09_AppearOneCh {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int res = getFirstAppearOne(str);
        if (res == -1) {
            System.out.println(-1);
        } else {
            System.out.println((char) res);
        }
    }

    public static int getFirstAppearOne(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }

        int[] hash = new int[256];
        int n = str.length();
        for (int i = 0; i < n; i++) {
            hash[str.charAt(i)]++;
        }
        for (int i = 0; i < n; i++) {
            if (hash[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }
        return -1;
    }


}
