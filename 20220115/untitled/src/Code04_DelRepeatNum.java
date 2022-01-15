/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 9:57
 * Description:空间O(1)的情况下，删除重复的数字，返回的是新的长度。
 */
public class Code04_DelRepeatNum {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int fast = 1;
        int slow = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
