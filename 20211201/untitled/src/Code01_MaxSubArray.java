import java.util.*;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-01
 * Time: 19:58
 * Description: 最长递增子序列
 */

public class Code01_MaxSubArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int[] dp = getDp(arr);

        int max = 0; //长度
        int index = 0;
        int pre = Integer.MAX_VALUE; //记录的是dp值所对应的数据元素
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = i;
                pre = arr[i];
            } else if (dp[i] == max) {
                //有可能dp值相同，这两相同的dp所对应的数据进行比较，字典序
                if (arr[i] < pre) {
                    pre = arr[i];
                    max = dp[i];
                    index = i;
                }
            }
        }

        int[] res = new int[max];
        res[--max] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                res[--max] = arr[i];
                index = i;
            }
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    public static int[] getDp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        int N = arr.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0; //有效区
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = right;
            while (L <= R) {
                int mid = (L + R) / 2;
                if (arr[i] > ends[mid]) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            right = Math.max(right, L);
            ends[L] = arr[i];
            dp[i] = L + 1;
        }
        return dp;
    }
}
