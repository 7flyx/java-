/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-13
 * Time: 21:06
 * Description:剑指offer53 第二问 丢失的数字
 */
public class LeetCode53_MissingNumber {
    class Solution {
        public int missingNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int i = 0;
            for (; i < nums.length; i++) {
                if (i != nums[i]) {
                    break;
                }
            }
            return i;
        }
    }
}
