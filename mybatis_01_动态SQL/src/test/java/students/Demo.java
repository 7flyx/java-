package students;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-11
 * Time: 8:38
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 9, 6,3};
        int[] q = {10};
//        solution.minOperations(nums, q);
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        for (ArrayList<Integer> list : map.values()) {
            Arrays.sort(nums);
        }
        System.out.println("他的 " + lowerBound(nums, 10));
        System.out.println("我的 " + binarySearch(nums, 10));
    }

    static class Solution {
        public List<Long> minOperations(int[] nums, int[] queries) {
            if (nums == null || queries == null) {
                return new ArrayList<>();
            }
            /* https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/solutions/2191417/yi-tu-miao-dong-pai-xu-qian-zhui-he-er-f-nf55/ 解析
             */

            // 首先排序
            Arrays.sort(nums);
            int N = nums.length;
            // 计算前缀和
            long[] preSum = new long[N + 1];
            preSum[0] = 0;
            for (int i = 1; i <= N; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

            List<Long> ans = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                // 找到“正数”“负数”的分界线。line的左边是比 queries[i]小的
                int line = binarySearch(nums, queries[i]);
                long left = (long)line * queries[i] -  preSum[line] - preSum[0]; // 小于queries[i] 的面积
                long right = preSum[N] - preSum[line] - (N - line) * queries[i];
                ans.add(left + right); // 答案就是二者之和
            }
            return ans;
        }

        private int binarySearch(int[] nums, int target) {
            if (nums== null) {
                return 0;
            }
            int left = 0;
            int right = nums.length;
            int ans = 0;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    ans = mid;
                    right = mid - 1;
                }
            }
            return ans;
        }
    }

    private static int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right;
    }

    // >=target 的最左位置
    private static int binarySearch(int[] nums, int target) {
        if (nums== null) {
            return 0;
        }
        int left = 0;
        int right = nums.length;
        int ans = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

}
