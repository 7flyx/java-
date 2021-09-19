import java.io.*;
/**
 * Created by flyx
 * Description:
 * 假设有排成一行的N个位置，记为1~N，开始时机器人在M位置，机器人可以往左或者往右走，
 * 如果机器人在1位置，那么下一步机器人只能走到2位置，如果机器人在N位置，
 * 那么下一步机器人只能走到N-1位置。规定机器人只能走k步，最终能来到P位置的方法有多少种。
 * 由于方案数可能比较大，所以答案需要对1e9+7取模。
 * User: 听风
 * Date: 2021-09-19
 * Time: 22:22
 */

public class Code03_RobotArrivePosition {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = in.readLine().split(" ");

        System.out.println(process2(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]),
                Integer.parseInt(nums[2]), Integer.parseInt(nums[3])));
        in.close();
    }

    public static int process1(int N, int M, int K, int P) {
        if (K == 0) {
            return M == P? 1 : 0;
        }

        if (M == 1) {
            return process1(N, M + 1, K - 1, P); //向右走一步
        }
        if (M == N) {
            return process1(N, M - 1, K - 1, P); //向左走一步
        }

        //在中间的位置上，向左向右
        int left = process1(N, M - 1, K - 1, P);
        int right = process1(N, M + 1, K - 1, P);
        return (left + right) % 1000000007;
    }

    public static int process2(int N, int M, int K, int P) {
        int[][] dp = new int[K + 1][N + 1];
        dp[K][P] = 1;
        //其余最后一行的所有数据都是0，只有到达P点的，才是1
        for (int i = K - 1; i >= 0; i--) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i + 1][j + 1]; //右下角的值
                } else if (j == N) {
                    dp[i][j] = dp[i + 1][j - 1]; //左下角的值
                } else {
                    dp[i][j] = (dp[i + 1][j - 1] + dp[i + 1][j + 1]) % 1000000007;
                }
            }
        }
        return dp[0][M];
    }

    public static int process3(int N, int M, int K, int P) {
        int[] dp = new int[N + 1]; //以总路程为长度
        dp[P] = 1; //到达终点的状况
        int left = 0;

        //dp空间压缩
        for (int i = 0; i < K; i++) {
            left = 0;
            for (int j = 1; j <= N; j++) {
                if (j == N) {
                    dp[j] = left; //左下角的值
                } else {
                    int tmp = dp[j]; //保存当前位置的值，作为新的left的值
                    dp[j] = (left + dp[j + 1]) % 1000000007;
                    left = tmp;
                }
            }
        }
        return dp[M] ;
    }
}