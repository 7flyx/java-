import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-12
 * Time: 20:57
 * Description:剑指offer61 扑克牌的顺子
 */
public class LeetCode61_SHUNZIOfCards {
    class Solution {
        public boolean isStraight1(int[] nums) {
            if (nums == null || nums.length != 5) {
                return false;
            }
            Arrays.sort(nums);
            int count = nums[1] == 0? 2 : (nums[0] == 0? 1 : 0); //统计大小王
            int start = nums[4]; //从最后一个元素开始
            int i = 4;
            while (i >= 0 && nums[i] != 0) {
                if (nums[i] == start) {
                    start--;
                    i--;
                } else {
                    start--;
                    count--;
                }
                if (count < 0) {
                    return false;
                }
            }
            return true;
        }

        public boolean isStraight(int[] nums) {
            if (nums == null || nums.length != 5) {
                return false;
            }
            Arrays.sort(nums);
            int k = -1; //记录大小王的最后出现位置
            for (int i = 0; i < 5; i++) {
                if (nums[i] == 0) {
                    k = i;
                    continue;
                } else if (i + 1 < 5 && nums[i] == nums[i + 1]) {
                    return false;
                }
            }

            return nums[4] - nums[k + 1] < 5;
        }
    }
}
