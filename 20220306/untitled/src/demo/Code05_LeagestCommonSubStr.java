package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-14
 * Time: 12:37
 * Description:
 */
public class Code05_LeagestCommonSubStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(getCommonSubStr(str1, str2));
        System.out.println(getCommonSubStr2(str1, str2));

        System.out.println("nvlrzqcjltmrejybjeshffenvkeqtbsnlocoyaokdpuxutrsmcmoearsgttgyyucgzgcnurfbubgvbwpyslaeykqhaaveqxijc".indexOf("gcn"));
        System.out.println("wkigrnngxehuiwxrextitnmjykimyhcbxildpnmrfgcnevjyvwzwuzrwvlomnlogbptornsybimbtnyhlmfecscmojrxekqmj".indexOf("gcn"));
    }

    // 经典dp-时间O(N*M)，空间O(N*M)
    public static String getCommonSubStr(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        // 根据二者的长度建表 str1纵向，str2横向
        int row = str1.length();
        int col = str2.length();
        if (row <= col) { // str1小的情况
            int[][] dp = new int[row + 1][col + 1];
            int max = 0; // 记录最长的长度
            int index = 0; // 记录匹配成功后 str1串的偏移量
            // 填写普通位置
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    // 当前字符相等的情况
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    }
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        index = i - 1; //较短字符串的偏移量
                    }
                }
            }
            if (max > 0) {
                return str1.substring(index - max + 1, index + 1); // 开始下标和结束下标 左闭右开区间
            }
        } else { // str2小的情况
            int[][] dp = new int[col + 1][row + 1];
            int max = 0; // 记录最长的长度
            int index = 0; // 记录匹配成功后 str1串的偏移量
            // 填写普通位置
            for (int j = 1; j <= col; j++) { // 行
                for (int i = 1; i <= row; i++) { // 列
                    // 当前字符相等的情况
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[j][i] = 1 + dp[j - 1][i - 1];
                    }
                    if (max < dp[j][i]) {
                        max = dp[j][i];
                        index = j - 1; //较短字符串的偏移量
                    }
                }
            }
            if (max > 0) {
                return str2.substring(index - max + 1, index + 1); // 开始下标和结束下标 左闭右开区间
            }
        }
        return "";
    }

    // dp压缩-时间O(N*M)，空间O(max(n, m))
    public static String getCommonSubStr2(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        // 根据二者的长度建表 str1纵向，str2横向
        int row = str1.length();
        int col = str2.length();
        if (row <= col) { // str1更短一点
            int max = 0;
            int index = 0;
            int[] dp = new int[col + 1];
            for (int i = 1; i <= row; i++) { // 行
                int leftUp = dp[0]; //左上角的值 dp[i-1][j-1]
                for (int j = 1; j <= col; j++) {
                    int up = dp[j]; //保存当前位置的上一个位置，后面将会变为左上角的值
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[j] = 1 + leftUp; // 自身长度+左上角的值
                    } else { // 不相等的话，当前位置就是0
                        dp[j] = 0;
                    }
                    if (max < dp[j]) {
                        max = dp[j];
                        index = i - 1; // 记录str1的偏移量。其实这里记录str1还是str2都是一样的
                    }
                    leftUp = up; //更新左上角的值
                }
            }
            if (max > 0) {
                return str1.substring(index - max + 1, index + 1);
            }
        } else { //str2更短一点
            int max = 0;
            int index = 0;
            int[] dp = new int[row + 1];
            for (int j = 1; j <= col; j++) { // 行
                int leftUp = dp[0];
                for (int i = 1; i <= row; i++) { // 列
                    int up = dp[i];
                    // str1的下标是i，str2的下标是j
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i] = 1 + leftUp;
                    } else {
                        dp[i] = 0;
                    }
                    if (max < dp[i]) {
                        max = dp[i];
                        index = j - 1; // 记录str2的偏移量
                    }
                    leftUp = up; //更新左上角的值
                }
            }
            if (max > 0) {
                return str2.substring(index - max + 1, index + 1);
            }
        }
        return "";
    }
    
}
