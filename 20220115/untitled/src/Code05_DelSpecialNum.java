/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-16
 * Time: 9:36
 * Description: 删除指定的数值
 */
public class Code05_DelSpecialNum {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            while (fast < nums.length && nums[fast] == val) {
                fast++;
            }
            if (fast < nums.length) {
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }
}
