/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-25
 * Time: 18:19
 * Description:剑指offer56 数组中只出现一次的数字，其他的数字都出现三次，问只出现一次的数字是？
 */
public class LeetCode56_NumberAppear {
    class Solution {
        public int singleNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int[] tmp = new int[32]; //记录一个数所有位置，出现三次的数，最后%3，就会略过
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < 32; j++) { //填写tmp表
                    tmp[j] += (nums[i] >> (31 - j)) & 1; //统计每一位
                }
            }
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res = (res << 1) |( tmp[i] % 3);
            }
            return res;
        }
    }
}
