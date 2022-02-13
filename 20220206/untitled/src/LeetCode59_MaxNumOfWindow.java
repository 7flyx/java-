import java.util.LinkedList;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-13
 * Time: 20:45
 * Description:剑指offer59题 滑动窗口的最大值
 */
public class LeetCode59_MaxNumOfWindow {
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k < 1) {
                return new int[]{};
            }
            if (nums.length < k) { //数组总长度小于窗口
                int[] res = {nums[0]};
                for (int i = 1; i < nums.length; i++) {
                    if(res[0] < nums[i]) {
                        res[0] = nums[i];
                    }
                }
                return res;
            }
            LinkedList<Integer> window = new LinkedList<>(); //滑动窗口
            int[] res = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                while (window.size() != 0 && nums[window.getLast()] < nums[i]) {
                    window.removeLast(); //删除最后的元素
                }
                window.add(i);
                if (i >= k - 1) {
                    res[i - k + 1] = nums[window.getFirst()];
                }
                if (window.getFirst() <= i + 1 - k) {
                    window.removeFirst(); //下一次的值已经超出窗口了，删除
                }
            }
            return res;
        }
    }
}
