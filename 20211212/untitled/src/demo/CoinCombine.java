package demo;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-12
 * Time: 21:45
 * Description:
 * 有1分，2分，5分，10分四种硬币，每种硬币数量无限，给定n分钱(n <= 100000)，有多少中组合可以组成n分钱
 */
public class CoinCombine {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(moneyCombine(n)); //递归尝试
        System.out.println(moneyCombine2(n)); //经典dp。有枚举过程
        System.out.println(moneyCombine3(n)); //经典dp，有一定范围上的加速，还是存在枚举过程
        System.out.println(moneyCombine4(n)); //优化后的dp，省略了枚举过程
        System.out.println(moneyCombine5(n)); //dp空间压缩
    }

    //递归尝试
    public static int moneyCombine(int n) {
        if (n < 0) {
            return 0;
        }

        int[] coins = {1, 2, 5, 10};
        return process(coins, n, 0, 0);
    }

    /**
     * 暴力递归解法
     *
     * @param coins 硬币面值
     * @param n     目标的钱数
     * @param cur   当前的钱数
     * @param index 面值的下标值，潜规则，必须先把一种硬币使用完才能只用下一种硬币。
     * @return 返回最终的方法数
     */
    public static int process(int[] coins, int n, int cur, int index) {
        if (cur == n) {
            return 1;
        }
        if (cur > n || index == coins.length) {
            return 0;
        }
        int res = 0;
        res += process(coins, n, cur, index + 1); //当前位置的钱，我不想要
        for (int i = cur + coins[index]; i <= n; i += coins[index]) {
            res += process(coins, n, i, index + 1); //当前位置的钱，我要了
        }
        return res;
    }

    //经典的dp
    public static long moneyCombine2(int n) {
        if (n < 0) {
            return -1;
        }

        int[] coins = {1, 2, 5, 10};
        //根据递归的写法，可变参数也就是两个
        int[][] dp = new int[5][n + 1]; //行是硬币数，列是当前的钱数，返回左上角的值
        dp[4][n] = 1; //填写最后一行的数据，除了右下角是1，其他的全是0

        //整体是从下往上，从左往右的
        for (int index = 3; index >= 0; index--) { //从倒数第二行开始填写
            for (int money = 0; money <= n; money++) { //从第一列开始填写
                dp[index][money] = dp[index + 1][money]; //当前位置的硬币不要
                for (int cur = money + coins[index]; cur <= n; cur += coins[index]) { //递归过程的枚举过程
                    dp[index][money] += dp[index + 1][cur];
                    dp[index][money] %= 1000000007;
                }
            }

        }

        return dp[0][0] % 1000000007;
    }

    //经典的dp，答案在左上角，遍历是从左往右的，有枚举过程。枚举时可能会溢出
    public static long moneyCombine3(int n) {
        if (n < 0) {
            return -1;
        }

        int[] coins = {1, 2, 5, 10};
        //根据递归的写法，可变参数也就是两个
        long[][] dp = new long[5][n + 1]; //行是硬币数，列是当前的钱数，返回左上角的值
        dp[4][n] = 1; //填写最后一行的数据，除了右下角是1，其他的全是0

        //整体是从下往上，从左往右的
        for (int index = 3; index >= 0; index--) { //从倒数第二行开始填写
            for (int money = 0; money <= n; money++) { //从第一列开始填写
                dp[index][money] = dp[index + 1][money]; //当前位置的硬币不要
                if (money - coins[index] >= 0) {
                    dp[index][money] = dp[index][money - coins[index]] - dp[index + 1][money - coins[index]];
                    dp[index][money] %= 1000000007;
                } else {
                    for (int cur = money + coins[index]; cur <= n; cur += coins[index]) { //递归过程的枚举过程
                        dp[index][money] += dp[index + 1][cur];
                        dp[index][money] %= 1000000007;
                    }
                }
            }
        }
        return dp[0][0] % 1000000007;
    }

    //优化的dp，从右往左遍历，没有枚举过程
    public static int moneyCombine4(int n) {
        if (n < 0) {
            return -1;
        }

        int[] coins = {1, 2, 5, 10};
        //根据递归的写法，可变参数也就是两个
        int[][] dp = new int[5][n + 1]; //行是硬币数，列是当前的钱数，返回左上角的值
        dp[4][n] = 1; //填写最后一行的数据，除了右下角是1，其他的全是0

        //整体是从下往上，从右往左的
        for (int index = 3; index >= 0; index--) { //从倒数第二行开始填写
            for (int money = n; money >= 0; money--) { //从最后一列开始填写
                if (money + coins[index] <= n) {
                    dp[index][money] = dp[index][money + coins[index]] + dp[index + 1][money];
                    dp[index][money] %= 1000000007;
                } else {
                    dp[index][money] = dp[index + 1][money]; //直接拿取下边的值
                }
            }
        }

        return (int) dp[0][0];
    }

    //dp空间压缩
    public static int moneyCombine5(int n) {
        if (n < 0) {
            return 0;
        }

        int[] coins = {1, 2, 5, 10};
        int[] dp = new int[n + 1]; //一维数组，从右向左开始填写
        dp[n] = 1; //右下角的值是1
        for (int index = 3; index >= 0; index--) {//行数
            for (int money = n; money >= 0; money--) {
                if (money + coins[index] <= n) {
                    dp[money] += dp[money + coins[index]];
                    dp[money] %= 1000000007;
                } //else情况，也就是直接拿取它下边的值，在这里就直接不动了
            }
        }
        return dp[0];
    }
}

