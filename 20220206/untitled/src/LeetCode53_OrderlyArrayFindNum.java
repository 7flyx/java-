/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-12
 * Time: 19:51
 * Description:剑指offer53 有序数组中统计数字出现的次数
 */
public class LeetCode53_OrderlyArrayFindNum {
    class Solution {
        public int search1(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int res = 0;
            for (int i = 0; i < nums.length && nums[i] <= target; i++) {
                if(nums[i] == target) {
                    res++;
                }
            }
            return res;
        }

        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int res = 0;
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[mid] == target) {
                    l = mid - 1;
                    r = mid + 1;
                    res++;
                    while (l >= 0 && nums[l--] == target) {
                        res++;
                    }
                    while (r < nums.length && nums[r++] == target) {
                        res++;
                    }
                    break; //跳出循环
                } else  if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return res;
        }
    }
}
