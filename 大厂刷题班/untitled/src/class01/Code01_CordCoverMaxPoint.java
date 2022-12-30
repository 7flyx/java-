package class01;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:09
 * Description: 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 */
public class Code01_CordCoverMaxPoint {
    public static void main(String[] args) {
        int testTime = 100;
        int range = 100;
        int length = 50;
        System.out.println("test start.");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateArray(length, range);
            int K = Math.abs((int)(Math.random() * length) - (int)(Math.random() * length));
            int ans1 = maxPoint1(arr, K);
            int ans2 = maxPoint2(arr, K);
            if (ans1 != ans2) {
                System.out.println("test fail.");
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println("test finish.");
    }

    // 滑动窗口解 O(N)
    public static int maxPoint1(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        // 滑动窗口 闭区间
        int left = 0;
        int right = 0; // 以right处的点作为终点，往左边进行覆盖。
        int ans = 0;
        int N = arr.length;
        while (right < N) {
            while (right + 1 < N && arr[right + 1] - arr[left] <= K) {
                right++;
            }
            ans = Math.max(ans, right - left + 1);
            left++; // 下一次循环，left~right之间的距离一定是超过了K，所以left需要往右移动
            if (right + 1 == N) { // right已经到了最后一个元素的位置，可以提前退出循环了
                break;
            }
        }
        // 因为是以right作为终点的，所以主要right走完了这个数组长度就行
        return ans;
    }

    // 暴力解O(N^2)
    public static int maxPoint2(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 1; // 只要K大于0，至少是能覆盖一个点的
        for (int end = 0; end < N; end++) { // 终点
            for (int start = 0; start < end; start++) { // 起点
                if (arr[end] - arr[start] > K) {
                    continue;
                }
                ans = Math.max(ans, end - start + 1); // start end是闭区间，所以需要+1
            }
        }
        return ans;
    }

    // 生成一个 升序且不含重复值的数组
    public static int[] generateArray(int length, int range) {
        int[] arr = new int[length];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            int num = (int) (Math.random() * range) + 1;
            while (set.contains(num)) {
                num = (int) (Math.random() * range) + 1;
            }
            arr[i] = num;
            set.add(num);
        }
        Arrays.sort(arr);
        return arr;
    }
}
