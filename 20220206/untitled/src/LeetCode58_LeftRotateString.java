/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-11
 * Time: 19:29
 * Description:LeetCode剑指offer58 左旋转字符串。
 */
public class LeetCode58_LeftRotateString {
    // 从n位置开始娶字符，到了末尾后，又从0位置开始。也就是取模运算
    public String reverseLeftWords(String s, int n) {
        if (s == null || n < 1) {
            return s;
        }

        int len = s.length();
        if (n > len) { //可能会有很多旋转，取模，去除多余的
            n %= len;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < len + n; i++) {
            sb.append(s.charAt(i % len));
        }
        return sb.toString();
    }
}
