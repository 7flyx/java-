package class02;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-02-16
 * Time: 18:51
 * Description:
 * 给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长。（默认是升序）
 */
public class Code06_MinLengthForSort {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        System.out.println(minLengthOfSubArray(arr));
    }

    public static int minLengthOfSubArray(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        // 定义一个arr[0] ，往右扫描
        int leftMax = Integer.MIN_VALUE;
        int rightRange = 0;
        for (int i = 0; i < arr.length; i++) { // 找左部分的最大值，最多向右边移动到哪个位置
            if (leftMax > arr[i]) { // 说明leftMax和arr[i]要交换位置
                rightRange = i; // 待排序的子数组的右边界
            }
            leftMax = Math.max(leftMax, arr[i]);
        }
        int rightMin = Integer.MAX_VALUE;
        int leftRange = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (rightMin < arr[i]) { // 说明rightMin和arr[i]要交换位置
                leftRange = i;
            }
            rightMin = Math.min(rightMin, arr[i]);
        }
        return Math.max(0, rightRange - leftRange + 1);
    }


}
