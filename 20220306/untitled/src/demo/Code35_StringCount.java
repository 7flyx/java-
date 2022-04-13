package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-13
 * Time: 10:36
 * Description: 4月13号 第一个代码题 字符串计数
 */

import java.util.Scanner;

public class Code35_StringCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = sc.next();
            int len1 = sc.nextInt();
            int len2 = sc.nextInt();
            long count1 = getCount(s1, len1, len2);
            long count2 = getCount(s2, len1, len2);
            System.out.println(count1 - count2 - 1);
            System.out.println(process(s1, s2, len1, len2));
        }
    }

    public static long getCount(String str, int len1, int len2) {
        if (str == null || len2 < len1) {
            return 0;
        }
        long count = 0;
        char[] ch = str.toCharArray();
        for (int len = len1; len <= len2; len++) {
            for (int i = 0; i < len && i < ch.length; i++) { // 遍历字符数组
                count += (26 - (ch[i] - 'a' + 1)) * Math.pow(26, len - (i + 1)) % 1000007;
                count %= 1000007;
            }
            if (len > ch.length) { // 当前遍历的len已经超过了字符串的长度
                count += Math.pow(26, len - ch.length) % 1000007;
            }
        }
        return count % 1000007;
    }

    private static int process(String str1, String str2, int len1, int len2) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        long res = 0;
        for (int i = len1; i <= len2; i++) {
            char a = ch1[0];
            char b = ch2[0];
            res += (long) Math.pow(26, i - 1) * (b - a);
            long sumA = 0;
            long sumB = 0;
            for (int j = 1; j < ch1.length; j++) { // 统计当前字符串一直到'a'之间的字符串个数
                sumA = sumA + (ch1[j] - 'a') * (long) Math.pow(26, i - 1 - j);
            }
            for (int j = 1; j < ch2.length; j++) { // 统计当前字符串一直到'a'之间的字符串个数
                sumB = sumB + (ch2[j] - 'a') * (long) Math.pow(26, i - 1 - j);
            }
            res += sumB - sumA; // 加上 长的字符串总和 - 短的字符串总和
        }
        res--;
        res = res % 1000007;
        return (int) res;
    }
}
