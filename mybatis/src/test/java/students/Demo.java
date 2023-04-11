package students;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-11
 * Time: 8:38
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            System.out.println(longestCommonSubString(s1, s2));
        }
    }

    public static String longestCommonSubString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        String shortString = s1.length() < s2.length()? s1 : s2;
        String longString = shortString == s1? s2 : s1;
        int shortLen = shortString.length();
        int longLen = longString.length();

        int[][] dp = new int[shortLen][longLen];
        // 第一行
        for (int j = 0; j < longLen; j++) {
            if (shortString.charAt(0) == longString.charAt(j)) {
                dp[0][j] = 1;
            }
        }
        // 第一列
        for (int i = 0; i < shortLen; i++) {
            if (shortString.charAt(i) == longString.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        int length = 0;
        int index = 0;
        // 填写普遍位置
        for (int i = 1; i < shortLen; i++) {
            for (int j = 1; j < longLen; j++) {
                if (shortString.charAt(i) == longString.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (length < dp[i][j]) {
                    length = dp[i][j];
                    index = i;
                }
            }
        }
        String ans = shortString.substring(index - length + 1, index + 1);
        return ans;
    }

}
