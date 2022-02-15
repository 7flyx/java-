/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-15
 * Time: 20:23
 * Description:剑指offer58 翻转单词顺序
 */
public class LeetCode58_ReverseWord {
    class Solution {
        public String reverseWords(String s) {
            if (s == null) {
                return "";
            }

            char[] ch = s.trim().toCharArray(); //去掉前后的空格
            StringBuilder sb = new StringBuilder();
            int left = ch.length - 1;
            int right = left;
            while (left >= 0) {
                while (left >= 0 && ch[left] != ' ') {
                    left--;
                }
                sb.append(new String(ch, left + 1, right - left));
                while (left >= 0 && ch[left] == ' ') { //跳过单词之间多余的空格
                    left--;
                }
                right = left;
                if (left >= 0) { //后面还有单词的情况，需要在中间加入空格
                    sb.append(' ');
                }
            }
            return sb.toString();
        }

    }
}
