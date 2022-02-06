/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 19:28
 * Description:leetcode 剑指offer50 第一个只出现一次的字符
 */
public class LeetCode50_FirstUniqChar {
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }

        char[] ch = s.toCharArray();
        char[] dp = new char[26];
        char res = 0;
        for (int i = ch.length - 1; i >= 0; i--) {
            dp[ch[i] - 'a']++;
        }
        for (int i = 0; i < ch.length; i++) {
            if (dp[ch[i] - 'a'] == 1) {
                return ch[i];
            }
        }
        return ' ';
    }
}
