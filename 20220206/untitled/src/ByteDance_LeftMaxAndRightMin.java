import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-23
 * Time: 13:33
 * Description: 今年字节三面 原创题
 * 给定一个数组，计算数组中，比左边的数都大，并且比右边的数都小的，有哪些，返回下标。时间O(N)
 */
public class ByteDance_LeftMaxAndRightMin {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 8, 9, 20, 12};
        List<Integer> res = getLeftAndRight(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }

    public static List<Integer> getLeftAndRight(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        //建立一张表，从后向前
        int N = nums.length;
        int[] rightMin = new int[N];
        rightMin[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rightMin[i] = Math.min(nums[i], rightMin[i + 1]); //取最小值
        }

        int preMax = nums[0];
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < N - 1; i++) {
            if (nums[i] > preMax && nums[i] < rightMin[i + 1]) {
                res.add(i);
            }
            preMax = Math.max(preMax, nums[i]);
        }
        return res;
    }
}
