package demo;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-10-24
 * Time: 9:08
 * Description:
 * 零食问题 & 世界冰球锦标赛
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]
 * 牛牛想知道在总体积不超过背包容量的情况下
 * 一共有多少种零食放法(总体积为0也算一种放法)
 * 数据量描述:
 * 1 <= n <= 40, 1 <= w <= 2 * 10^9, 0 <= v[i] <= 10^9
 * 测试链接 : https://www.nowcoder.com/practice/d94bb2fa461d42bcb4c0f2b94f5d4281
 * 测试链接 : https://www.luogu.com.cn/problem/P4799
 */
public class BagProblemOfNiuNiu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int)in.nval; in.nextToken(); // 零食数量
            long w = (long)in.nval; // 背包容量
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                in.nextToken(); nums[i] = (long)in.nval;
            }
            out.println(compute(nums, n, w));
        }
        out.flush();
        out.close();
    }

    static int MAXN = 40;
    static int MAXM = 1 << (MAXN / 2);
    static long[] lsum = new long[MAXM];
    static long[] rsum = new long[MAXM];
    static int fill;
    private static long compute(long[] nums, int n, long w) {
        // 因为数据量比较大，直接使用背包模型，肯定是爆内存的，所以需要折半，对数组的左右部分分别暴力求解
        // 然后对左右部分的数据，进行整合就行
        // 时间复杂度分析：n最大是30，左右部分 分别暴力是：2^15 + 2^15
        // 然后对暴力的结果进行排序，使用双指针整合数据
        fill = 0;
        f(nums, 0, n / 2, w, 0, lsum);
        int lSize = fill;
        fill  = 0;
        f(nums, n / 2, n, w, 0, rsum);
        int rSize = fill;

        Arrays.sort(lsum, 0, lSize);
        Arrays.sort(rsum, 0, rSize);
        long ans = 0;
        for (int l = 0, r = rSize - 1; l < lSize; l++) {
            while (r >= 0 && lsum[l] + rsum[r] > w) {
                r--;
            }
            if (lsum[l] + rsum[r] <= w)
                ans += r + 1;
        }
        return ans;
    }

    private static void f(long[] nums, int i, int end, long w, long sum, long[] ans) {
        if (i == end) {
            ans[fill++] = sum;
        } else {
            f(nums, i + 1, end, w, sum, ans);
            if (sum + nums[i] <= w) {
                f(nums, i + 1, end, w, sum + nums[i], ans);
            }
        }
    }
}
