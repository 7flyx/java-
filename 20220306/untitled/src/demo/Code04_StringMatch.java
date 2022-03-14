package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-12
 * Time: 15:33
 * Description: 3月12号 强训题 第2个代码题 字符串通配符
 * https://www.nowcoder.com/questionTerminal/43072d50a6eb44d2a6c816a283b02036
 */
public class Code04_StringMatch {
    private static int N;
    private static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine(); // 模式串
        String tmp = sc.nextLine(); // 主串
        System.out.println(isMatchString(str, tmp));
    }

    public static boolean isMatchString(String str, String tmp) {
        if (str == null || tmp == null) {
            return false;
        }

        N = str.length();
        M = tmp.length();
        // return process(str, tmp, 0, 0);
        return process2(str, tmp);
    }

    // index1指向 str模式串， index2指向tmp主串
    public static boolean process(String str, String tmp, int index1, int index2) {
        if (index1 == N && index2 == M) {
            return true;
        }
        if (index2 >= M || index1 >= N) {
            return false; // 主串还没结束 但模式串已经结束，则匹配失败
        }

        if (isEquals(str.charAt(index1), tmp.charAt(index2))) {
            return process(str, tmp, index1 + 1, index2 + 1);
        } else if (str.charAt(index1) == '?' && isLegalCh(tmp.charAt(index2))) {
            return process(str, tmp, index1 + 1, index2 + 1);
        } else if (str.charAt(index1) == '*') {
            // 匹配0个 1个或多个
            boolean res = false;
            res |= process(str, tmp, index1 + 1, index2);
            if (isLegalCh(tmp.charAt(index2))) {
                res |= process(str, tmp, index1 + 1, index2 + 1);
                res |= process(str, tmp, index1, index2 + 1);
            }
            return res;
        }
        return false;
    }

    public static boolean process2(String str, String tmp) {
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        // 最后一行和最右一列都是false

        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (isEquals(str.charAt(i), tmp.charAt(j))) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (str.charAt(i) == '?' && isLegalCh(tmp.charAt(j))) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (str.charAt(i) == '*') {
                    // 匹配0个 1个或多个
                    dp[i][j] |= dp[i + 1][j];
                    if (isLegalCh(tmp.charAt(j))) {
                        dp[i][j] |= dp[i + 1][j + 1];
                        dp[i][j] |= dp[i][j + 1];
                    }
                }
                // return false ； 默认就是false
            }
        }
        return dp[0][0];
    }


    // 判断两个字符是否相等，不区分大小写
    public static boolean isEquals(char ch1, char ch2) {
        if (ch1 == ch2) {
            return true;
        }
        // 两个都是字符的情况
        if (((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z')) &&
                ((ch2 >= 'a' && ch2 <= 'z') || (ch2 >= 'A' && ch2 < 'Z'))) {
            if ((ch1 - 32) == ch2 || (ch2 - 32) == ch1) {
                return true; //二者差距是32，则表示是大小写字符
            }
        }
        return false;
    }

    public static boolean isLegalCh(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
            return true;
        }
        return false;
    }

}
