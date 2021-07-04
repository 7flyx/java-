package demo1;

public class demo3 {
    public static void main(String[] args) {
        /*
                假设有排成一行的N个位置，记为1~N,N一定大于等于2
                一开始，机器人在M的位置上
                M = 1时， 此时机器人只能往右走（最左边的时候）
                M = N时，此时机器人只能往左走 （最右边的时候）
                M在（1~N）之间时，左右都可以走 （中间的时候）
                机器人每次只能走一步，现在规定机器人必须走完K步，机器人最终来到的位置是P（P在 1~N 的范围内）
                给定四个参数（N，M, K, P）， 问共有多少种走法
         */
        //暴力递归，穷举所有的可能性
        //动态规划---将所有暴力递归重复的计算省去，用到缓冲。  记忆化搜索

        int num = walkWay2(7, 3, 4, 5);
        System.out.println(num);
    }

    public static int walkWay2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[8][5]; //根据N确定行数， 走的步数确定列数
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return countNum2(N, M, K, P, dp);
    }

    public static int countNum1(int N, int cur, int rest, int P) {
        //暴力递归的解法
        if (rest == 0 ) {
            if (cur == P) {
                return 1; //剩余步数也完了，此时的cur也到了终点
            }
            return 0;
        }
        if (cur == 1) {
            return countNum1(N, cur + 1, rest - 1, P); //只能往右走
        }
        if (cur == N) {
            return countNum1(N, cur - 1, rest - 1, P); //只能往左走
        }
        return countNum1(N, cur + 1, rest - 1, P) + countNum1(N, cur - 1, rest - 1, P); //左右两条路
    }

    public static int countNum2(int N, int cur, int rest, int P, int[][] dp) {
        //用一个二维数组存储已经计算出来的信息，当然不仅限于二维数组
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        if (rest == 0) {
            if (cur == P) {
                dp[cur][rest] = 1;
                return dp[cur][rest];
            }
            dp[cur][rest] = 0;
            return dp[cur][rest];
        }

        if (cur == 1) {
            dp[cur][rest] = countNum2(N, cur + 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        if(cur == N) {
            dp[cur][rest] = countNum2(N, cur - 1, rest - 1, P, dp);
            return dp[cur][rest];
        }

        dp[cur][rest] = countNum2(N, cur + 1, rest - 1, P, dp) + countNum2(N, cur - 1, rest - 1, P, dp);
        return dp[cur][rest];
    }
}
