package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-09
 * Time: 17:29
 * Description: 强训题 3月9号 第二道代码题 -手套
 *  给你一个整数n，表示有n种颜色的手套。再给你两个数组left,right
 *  分别表示左手套和右手套每种颜色的数量，问你 在看不见颜色情况下，
 *  最少一次性拿出多少只手套，保证一定能拿到一双颜色相同的手套。
 *  例如 n = 4
 *  left = {0, 6, 1, 7}
 *  right = {1, 5, 0, 6}
 *  答案 10
 */
public class Code02_GetMininumGlove {
    public int findMinimum(int n, int[] left, int[] right) {
        if (n < 1 || left == null || right == null) {
            return 0;
        }
        int sum = 0;
        int leftSum = 0; //左手总和
        int rightSum = 0; //右手总和
        int leftMin = Integer.MAX_VALUE; // 左手最少的手套数
        int rightMin = Integer.MAX_VALUE; // 右手最少的手套数
        // 遍历两个数组
        for (int i = 0; i < n; i++) {
            if (left[i] == 0 || right[i] == 0) {
                sum += (left[i] + right[i]);
            } else {
                leftSum += left[i];
                rightSum += right[i];
                leftMin = Math.min(leftMin, left[i]);
                rightMin = Math.min(rightMin, right[i]);
            }
        }
        // 核心一点就是 在一堆手套中，要想保证100%能拿到每种颜色的手套
        // 只能是 总和 - 最少的手套 + 1, 这就是最坏的情况了
        return sum + Math.min(leftSum - leftMin + 1, rightSum - rightMin + 1) + 1;
    }

}
