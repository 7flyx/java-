package demo1;

public class demo1 {
    public static void main(String[] args) {
        //动态规划
        /*
                给定数组arr，设数组长度为n，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，
                每种面值的货币可以使用任意张，再给定一个整数aim，代表要找的钱数，求换钱的方法数有多少种。
         */

        int[] arr = {2, 3, 5, 7, 10};
        int[] arr2 = {5, 10, 25, 1};
        int aim = 1000;
        // System.out.println(num1(arr, aim));
        System.out.println(num1(arr2, 15));
        System.out.println(num2(arr2, 15));
        System.out.println(num3(arr2, 15));
        System.out.println(num4(arr2, 15));
    }

    public static int num1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        //暴力递归解法
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int aim) {
        if (aim == 0) {
            return 1;
        } else if (index == arr.length) {
            return 0;
        }

        int res = 0;
        for (int k = 0; k * arr[index] <= aim; k++) {
            res += process1(arr, index + 1, aim - (k * arr[index]));
        }
        return res;
    }

    public static int num2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }

        //根据暴力递归，改记忆化搜索
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        //自顶向下法
        return process2(arr, 0, aim, dp);
    }

    public static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) { //已经计算过，直接返回
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int res = 0;
        for (int k = 0; k * arr[index] <= rest; k++) {
            res += process2(arr, index + 1, rest - k * arr[index], dp);
        }
        dp[index][rest] = res;
        return res;
    }

    public static int num3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }

        //再次对上面的记忆化搜索进行修改
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; //dp[N][1....aim] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                //dp[index][rest] = ?;
                int res = 0;
                for (int k = 0; k * arr[index] <= rest; k++) {
                    res += dp[index + 1][rest - (k * arr[index])];
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][aim]; //暴力递归中，index从0开始，要达到aim的值，所以就是0行aim列
    }

    public static int num4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }

        //再次对上面的记忆化搜索进行修改
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; //dp[N][1....aim] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //dp[index][rest] = ?;
                //再次进行修改，上面的num3有枚举行为，经过推算，
                // dp[index][rest] = dp[index+1][rest] + dp[index][rest - arr[index]].再一次将重复
                //计算的行为抹去，称为经典动态规划
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim]; //暴力递归中，index从0开始，要达到aim的值，所以就是0行aim列
    }
}
