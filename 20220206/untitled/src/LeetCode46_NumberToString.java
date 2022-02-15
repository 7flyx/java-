/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-15
 * Time: 20:57
 * Description:剑指offer46 数字转字符串的个数
 */
public class LeetCode46_NumberToString {
    class Solution {
        public int translateNum1(int num) {
            if (num < 0) {
                return 0;
            }
            char[] ch = (num + "").toCharArray();
            return process(ch, 0);
        }

        private int process(char[] ch, int index) {
            if (index >= ch.length) {
                return 1;
            }
            int res = process(ch, index + 1); //只看做一个数的情况
            if (index + 1 < ch.length && ch[index] - '0' == 2 &&
                    (ch[index + 1] >= '0' && ch[index + 1] <= '5')) { //20 ~ 25范围内
                res += process(ch, index + 2);
            }
            if (index + 1 < ch.length && ch[index] - '0' == 1) { //10 ~ 19范围内
                res += process(ch, index + 2);
            }
            return res;
        }

        //经典dp
        public int translateNum2(int num) {
            if (num < 0) {
                return 0;
            }
            char[] ch = (num + "").toCharArray();
            int[] dp = new int[ch.length + 1];
            dp[ch.length] = 1; //递归结束条件
            for (int i = ch.length - 1; i >= 0; i--) {
                dp[i] = dp[i + 1]; //只看做一个数的时候,拿后续位置的值
                if (ch[i] == '1' && i + 1 < ch.length) {
                    dp[i] += dp[i + 2];
                }
                if (ch[i] == '2' && i + 1 < ch.length && ch[i + 1] >= '0' && ch[i + 1] <= '5') {
                    dp[i] += dp[i + 2];
                }
            }
            return dp[0];
        }

        //空间压缩，因为i的计算，只会依赖于i+1和i+2的值
        public int translateNum(int num) {
            if (num < 0) {
                return 0;
            }
            char[] ch = (num + "").toCharArray();
            int left = 1; //i+1
            int right = 1; //i + 2
            for (int i = ch.length - 1; i >= 0; i--) {
                int cur = left; //只看做一个数的时候,拿后续位置的值
                if (ch[i] == '1' && i + 1 < ch.length) {
                    cur += right; // i + 2
                }
                if (ch[i] == '2' && i + 1 < ch.length && ch[i + 1] >= '0' && ch[i + 1] <= '5') {
                    cur += right; // i + 2
                }
                right = left;
                left = cur; //更新二者的值
            }
            return left;
        }
    }
}
