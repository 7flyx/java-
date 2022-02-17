import java.util.HashSet;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-17
 * Time: 19:38
 * Description:剑指offer57 和为s的两个数字
 */
public class LeetCode57_SumOfTwoNum {
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null) {
            return new int[]{};
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
            set.add(nums[i]);
        }
        return new int[]{};
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return new int[]{};
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return new int[]{};
    }
}
