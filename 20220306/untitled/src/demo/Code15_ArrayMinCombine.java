package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-25
 * Time: 14:07
 * Description: 3月25号 第一个代码题
 */
public class Code15_ArrayMinCombine {
    public static class Solution {
        /**
         * 正数数组中的最小不可组成和
         * 输入：正数数组arr
         * 返回：正数数组中的最小不可组成和
         */
        private boolean[] dp;
        private int max;
        private int min = Integer.MAX_VALUE;
        public int getFirstUnFormedNum(int[] arr) {
            if (arr == null) {
                return 0;
            }
            for (int i = 0; i < arr.length; i++) {
                max += arr[i];
                min = Math.min(min, arr[i]);
            }
            dp = new boolean[max + 1];
            p(arr, 0, 0);
            for (int i = min; i <= max; i++) {
                if (!dp[i]) {
                    return i;
                }
            }
            return max + 1;
        }

        public void p(int[] arr, int index, int sum) {
            if (index == arr.length) {
                dp[sum] = true;
                return;
            }

            p(arr, index + 1, sum); // 不要当前数
            p(arr, index + 1, sum + arr[index]); // 要当前数
        }
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        int[] arr = {17};
        System.out.println(so.getFirstUnFormedNum(arr));
    }
}
