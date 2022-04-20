package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-20
 * Time: 17:00
 * Description: 4月20号 第一个代码题 乒乓球
 */
public class Code43_PingPengBall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split("<br/>");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                String[] tmp = str[i].split(" ");
                sb.append(pingPengBall(tmp[0], tmp[1])? "Yes" : "No");
                if (i + 1 < str.length) {
                    sb.append("<br/>");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static boolean pingPengBall(String a, String b) {
        if (a == null || b == null || a.length() < b.length()) {
            return false;
        }
        int[] count = new int[26]; // 用于统计a中的字符个数
        int n = a.length();
        int m = b.length();
        for (int i = 0; i < n; i++) {
            count[a.charAt(i) - 'A']++;
        }

        for (int i = 0; i < m; i++) {
            char ch = b.charAt(i);
            if (count[ch - 'A'] == 0) {
                return false;
            }
            count[ch - 'A']--;
        }

        return true;
    }
}
