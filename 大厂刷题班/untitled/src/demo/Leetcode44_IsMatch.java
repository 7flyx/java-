package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-08
 * Time: 13:20
 * Description:
 */
public class Leetcode44_IsMatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine().toLowerCase(); // 有通配符
        String s2 = sc.nextLine().toLowerCase(); // 待匹配的字符串
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        System.out.println(f(ch1, ch2, 0, 0));
        System.out.println(f2(ch1, ch2));
    }

    public static boolean f(char[] ch1, char[] ch2, int i, int j) {
        if (i == ch1.length && j == ch2.length) { // 来到了末尾, 则表示前面的都是匹配成功了的
            return true;
        }
        if (i == ch1.length && j < ch2.length) { // i1走完了，i2还没走完，直接false
            return false;
        }

        if (j < ch2.length && (ch1[i] != ch2[j] && !isLegal(ch2[j]))) { // 不合法字符
            return false;
        } else if (j < ch2.length && ch1[i] == ch2[j]) {  // 判断当前字符相不相等. 如果相等，直接往下走
            return f(ch1, ch2, i + 1, j + 1);
        }
        // 判断ch1是不是有通配符
        if (ch1[i] == '*') { // 匹配0个或多个字符
            boolean ans = false; // 匹配0个
            ans = f(ch1, ch2, i + 1, j);
            if (j < ch2.length) { // i2不越界
                ans |= f(ch1, ch2, i + 1, j + 1); // 匹配1个，用掉*
                ans |= f(ch1, ch2, i, j + 1); // 匹配多个
            }
            return ans;
        } else if (j < ch2.length && ch1[i] == '?') { // 匹配1个字符
            return f(ch1, ch2, i + 1, j + 1); // 必须匹配1个
        }
        // 即没有通配符，也不相等，直接false
        return false;
    }

    public static boolean f2(char[] ch1, char[] ch2) {
        int N = ch1.length;
        int M = ch2.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;

        for (int i = N - 1; i >= 0; i--) { // 行 -> i1
            for (int j = M; j >= 0; j--) { // 列 -> i2
                if (j < ch2.length && (ch1[i] != ch2[j] && !isLegal(ch2[j]))) { // 不合法字符
                    dp[i][j] = false;
                } else if (j < ch2.length && ch1[i] == ch2[j]) {  // 判断当前字符相不相等. 如果相等，直接往下走
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (ch1[i] == '*') { // 匹配0个或多个字符
                    boolean ans = false; // 匹配0个
                    ans = dp[i + 1][j];
                    if (j < ch2.length) { // i2不越界
                        ans |= dp[i + 1][j + 1]; // 匹配1个，用掉*
                        ans |= dp[i][j + 1]; // 匹配多个
                    }
                    dp[i][j] = ans;
                } else if (j < ch2.length && ch1[i] == '?') { // 匹配1个字符
                    dp[i][j] = dp[i + 1][j + 1]; // 必须匹配1个
                } else {
                    dp[i][j] = false; // 即没有通配符，也不相等，直接false
                }
            }
        }
        return dp[0][0];
    }

    private static boolean isLegal(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }
}
