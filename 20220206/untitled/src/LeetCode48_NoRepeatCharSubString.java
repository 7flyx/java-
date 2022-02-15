import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-15
 * Time: 18:41
 * Description:剑指offer 48 最长无重复字符的子串长度
 */
public class LeetCode48_NoRepeatCharSubString {
    class Solution {
        //最优解-只用哈希表
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.equals("")) {
                return 0;
            }

            //存储当前字符最后出现的位置
            HashMap<Character, Integer> map = new HashMap<>();
            int k = -1;
            int res = 1; //至少是一个空字符串
            int N = s.length();
            for (int i = 0; i < N; i++) {
                char ch = s.charAt(i);
                if (map.containsKey(ch)) {
                    k = Math.max(k, map.get(ch)); //与k比较，谁更靠后
                }
                res = Math.max(res, i - k); //与res取最大
                map.put(ch, i);
            }
            return res;
        }

//        dp表+哈希表实现的
        public int lengthOfLongestSubstring2(String s) {
            if (s == null || s.equals("")) {
                return 0;
            }

            int pre = 0; //上一次，这个字符出现的位置
            int res = 1; //至少是一个空字符串
            int N = s.length();
            int[] dp = new int[N]; //存储以每个位置的字符结尾，有多长
            dp[0] = 1;
            HashMap<Character, Integer> map = new HashMap<>(); //存储字符最后出现的位置
            char[] ch = s.toCharArray();
            map.put(ch[0], 0); //第一个字符
            for (int i = 1; i < N; i++) {
                dp[i] = dp[i - 1] + 1; //上一个字符的dp值+1
                //判断是否出现字符，且会影响到当前结果（位置在 上一个字符的范围内）
                if (map.containsKey(ch[i]) && map.get(ch[i]) >= i - dp[i - 1]) {
                    dp[i] = i - map.get(ch[i]); //与最后的字符做差值
                }
                res = Math.max(res, dp[i]);
                map.put(ch[i], i);
            }
            return res;
        }
    }
}
