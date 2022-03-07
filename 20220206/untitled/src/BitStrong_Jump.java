import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-06
 * Time: 22:45
 * Description: 笔试强训课-跳石板
 * 小易来到了一条石板路前，每块石板上从1挨着编号为:1、2.3......
 * 这条石板路要根据特殊的规则才能前进:对于小易当前所在的编号为K的石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
 * 例如:
 * N=4，M= 24:4->6->8->12->18->24
 * 于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
 */
public class BitStrong_Jump {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        System.out.println(getLeagestStep4(N, M));
    }

    public static int getLeagestStep(int N, int M) {
        if (N > M) {
            return -1;
        }
        return process(N, M);
    }

    //递归版本
    public static int process(int N, int M) {
        if (N == M) {
            return 0;
        }
        if (N > M) {
            return Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 2; i < N; i++) {
            if (N % i == 0) { //是N的一个约数
                int tmp = process(N + i, M);
                if (tmp != Integer.MAX_VALUE) {
                    res = Math.min(res, tmp);
                }
            }
        }
        return res + 1;
    }

    //经典dp，还是超时
    public static int getLeagestStep2(int N, int M) {
        if (N > M) {
            return -1;
        }

        // 行是 N，列是count
        int[] dp = new int[M + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); //全部填充为-1，表示没有值的情况
        dp[M] = 0; // 最后一个空是0
        for (int i = M - 1; i >= N; i--) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) { //能够整除，是约数的情况
                    if (i + j <= M && dp[i + j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i + j]);
                    }
                }
            }
            dp[i] += 1;
        }
        return dp[N];
    }

    //对经典dp的优化 还是超时
    public static int getLeagestStep3(int N, int M) {
        if (N > M) {
            return -1;
        }

        // 行是 N，列是count
        int[] dp = new int[M + 1];
        // Arrays.fill(dp, Integer.MAX_VALUE); //全部填充为-1，表示没有值的情况
        dp[M] = 1; // 最后一个空是0
        for (int i = M - 1; i >= N; i--) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) { //能够整除，是约数的情况
                    if (i + j <= M && dp[i + j] != 0) {
                        dp[i] = dp[i] == 0 ? dp[i + j] : Math.min(dp[i], dp[i + j]);
                    }
                }
            }
            dp[i] += 1;
        }
        return dp[N] - 1;
    }

    public static int getLeagestStep4(int N, int M) {
        if (N > M) {
            return -1;
        }

        // 行是 N，列是count
        int[] dp = new int[M + 1];
        // Arrays.fill(dp, Integer.MAX_VALUE); //全部填充为-1，表示没有值的情况
        dp[M] = 1; // 最后一个空是0
        for (int i = M - 1; i >= N; i--) {
            for (int j = 2; j + i <= M && j != i; j++) {
                if (i % j == 0) { //能够整除，是约数的情况
                    if (dp[i + j] != 0) {
                        dp[i] = dp[i] == 0 ? dp[i + j] : Math.min(dp[i], dp[i + j]);
                    }
                    if(dp[i + (i / j)] != 0) {
                        dp[i] = dp[i] == 0 ? dp[i + j] : Math.min(dp[i], dp[i + (i / j)]);
                    }
                }
            }
            dp[i] = dp[i] == 0 ? dp[i] : dp[i] + 1;
        }
        return dp[N] - 1;
    }

    public static int getLeagestStep5(int n, int m) {
        int[] dp = new int[m + 1]; //用于动规的记录表，dp[i]记录从n号石板跳到i号石板的最少步数，dp[i]=Integer.MAX_VALUE时为不可达
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        // 从左向右扩展，每遍历一个位置，就能够推导出后续的相应位置的值
        for (int i = n; i < m; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (int j = 2; (j * j) <= i; j++) {
                if (i % j == 0) { //j是i的约数
                    if (i + j <= m) {
                        dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                    }

                    if (i + (i / j) <= m) {
                        dp[i + (i / j)] = Math.min(dp[i + (i / j)], dp[i] + 1);
                    }
                }
            }
        }

        if (dp[m] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[m];
    }

    public static int getMininumStep(int n, int m) {
        if (n > m) {
            return -1;
        }

        // 惯性思维，是从最后一个格子往前推导，这就导致了，很多格子其实都已经没必要往下
        // 循环了，导致大量的时间浪费了，所以我们需要从左向右推导，在格子里等于最大值，
        // 表示在此前面的所有推导中，没有一个格子能够到达这里，所以没必要再计算了
        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); //全部填充为最大值
        dp[n] = 0; //起点处是0
        for (int i = n; i <= m; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue; // 前面的所有推导，都没有推导到这个格子，所以不可能其他的达到，直接跳过
            }
            for (int j = 2; j <= i / j; j++) { //枚举所有的约数
                if (i % j == 0) { //是约数的情况
                    // 能够约的情况是，j是一个约数，i / j 就是另外一个约数，二者都做计算即可
                    if (i + j <= m) {
                        // 当前i位置+1之后，与其原先的位置进行比较，谁小，谁就上
                        dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                    }
                    if (i + (i / j) <= m) {
                        // 其实就是与上一个if语句是对称的，
                        dp[i + (i / j)] = Math.min(dp[i + (i / j)], dp[i] + 1);
                    }
                }
            }
        }
        return dp[m] == Integer.MAX_VALUE? -1 : dp[m];
    }
}
