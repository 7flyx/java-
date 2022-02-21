/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-21
 * Time: 20:43
 * Description:剑指offer51 数组中的逆序对
 */
public class LeetCode51_ArrayReversePair {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};
        int[] arr = {2, 4, 3, 5, 1};
        System.out.println(reversePairs(arr));
    }

    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        return process(nums, 0, nums.length - 1);
    }

    private static int process(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1); //中间值
        int res = process(nums, l, mid);
        res += process(nums, mid + 1, r);
        res += merge2(nums, l, mid, r);
        return res;
    }

    //合并有序，并计算答案
    private static int merge1(int[] nums, int l, int mid, int r) {
        int windowR = mid + 1;
        int res = 0;
        //不回退技巧
        for (int i = l; i <= mid; i++) {
            while (windowR <= r && nums[i] > nums[windowR]) {
                windowR++;
            }
            res += (windowR - mid - 1);
        }
        //归并操作
        int[] tmp = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            tmp[index++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            tmp[index++] = nums[p1++];
        }
        while (p2 <= r) {
            tmp[index++] = nums[p2++];
        }
        for (int i = r; i >= l; i--) {
            nums[i] = tmp[--index];
        }
        return res;
    }

    private static int merge2(int[] nums, int l, int mid, int r) {
        int res = 0;
        //归并操作
        int[] tmp = new int[r - l + 1];
        int index = tmp.length - 1;
        int p1 = mid;
        int p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            res += nums[p1] > nums[p2] ? p2 - mid : 0;
            tmp[index--] = nums[p1] > nums[p2] ? nums[p1--] : nums[p2--];
        }
        while (p1 >= l) {
            tmp[index--] = nums[p1--];
        }
        while (p2 >= mid + 1) {
            tmp[index--] = nums[p2--];
        }
        for (int i = l; i <= r; i++) {
            nums[i] = tmp[++index];
        }
        return res;
    }
}
