package class01;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:14
 * Description: 给定一个数组arr，你可以在每个数字之前决定+或者-但是必须所有数字都参与，再给定一个数target
 * 请问最后算出target的方法数。（LeetCode494）
 */
public class Code07_TargetSum {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWays1(nums, target));
        System.out.println(p(nums, 0, target));
    }

    // 最优解，时间复杂度在O(N*sum)，空间也是
    public static int findTargetSumWays1(int[] nums, int target) {
        if (nums ==null || nums.length == 0) {
            return 0;
        }
        /*
            整体思路：每个数字都可以分为正数和负数，将正数、负数都划分成各自的区域，则就有了正负两个区域。
            假设正数区域叫 X， 负数区域叫Y，则 target = X - Y
            假设sum是nums数组全部变为正数的累加和。则 X + Y = sum
            则 target + sum = X - Y + sum -> target + sum = 2X ->   X = (target + sum) / 2
            由上述等式就可以推导出，将题目转移成 nums全是正数，凑成 X的方法数。
         */
        int sum = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            nums[i] = Math.abs(nums[i]);
            sum += nums[i];
        }
        int X = (target + sum)  / 2; // 转移成新的目标值X、只需在nums数组中凑出X值的方法数即可
        int[][] dp = new int[N + 1][X + 1];
        dp[N][0] = 1;
        for (int i =  N - 1; i >= 0; i--) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = dp[i + 1][j]; // 不要当前数最为最优结果
                if (j - nums[i] >= 0) { // 要当前值作为最优结果
                    dp[i][j] += dp[i + 1][j - nums[i]];
                }
            }
        }
        return dp[0][X];
    }

    // 时间复杂度O(N*(2*sum+1)) 。空间也是
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // process(nums, index, target);
        int sum = 0;
        int N = nums.length;
        for (int num : nums) {
            sum += Math.abs(num);
        }
        if (sum < Math.abs(target)) { // 全+ 或者全-，都无法凑够target
            return 0;
        }
        int[][] dp = new int[N + 1][sum * 2 + 1];
        // index = N 的时候，进行结算 base case
        // 0~sum-1是负数，sum=0，sum+1往后都是正数
        dp[N][sum] = 1; // 只有用完全部的数据，且还等于0的时候，才是其中的一种方法
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i + 1][j - nums[i]]; // -nums[i]
                }
                if (j + nums[i] < dp[0].length) {
                    dp[i][j] += dp[i + 1][j + nums[i]]; // +nums[i]
                }
            }
        }
        return dp[0][sum + target];
    }

    // 暴力解
    public static int p(int[] nums, int index, int target) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        return p(nums, index + 1, target - nums[index]) +
                p(nums, index + 1, target + nums[index]);
    }
}
