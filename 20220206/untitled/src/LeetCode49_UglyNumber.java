/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-17
 * Time: 19:54
 * Description: 剑指offer49 丑叔
 */
public class LeetCode49_UglyNumber {
    public static int nthUglyNumber(int n) {
        if (n < 7) {
            return n;
        }

        int count = 6; //前面已经有6个了
        int res = 0;
        for (int i = 7; count != n; i++) {
            int tmp = i;
            res = i;
            while (tmp % 2 == 0) {
                tmp /= 2;
            }
            while (tmp % 5 == 0) {
                tmp /= 5;
            }
            while (tmp % 3 == 0) {
                tmp /= 3;
            }
            if (tmp == 1) {
                count++;
            }
        }
        return res;
    }

    class Solution {
        public int nthUglyNumber(int n) {
            if(n < 7) {
                return n;
            }

            int[] dp = new int[n];
            dp[0] = 1; //第一个丑叔
            int a = 0, b = 0,c = 0;
            int va = 0, vb = 0, vc = 0;
            for (int i = 1; i < n; i++) {
                va = dp[a] * 2;
                vb = dp[b] * 3;
                vc = dp[c] * 5;
                //三者取最小的，即就是当前最小的丑叔，放入dp表中
                dp[i] = Math.min(Math.min(va, vb), vc);
                //计算出最新的丑叔后，相应计算丑叔的前者下标需要后移
                if (va == dp[i]) {
                    a++;
                }
                if (vb == dp[i]) {
                    b++;
                }
                if (vc == dp[i]) {
                    c++;
                }
            }
            return dp[n - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
