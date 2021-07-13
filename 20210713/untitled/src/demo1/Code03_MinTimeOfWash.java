package demo1;

public class Code03_MinTimeOfWash {
    public static void main(String[] args) {
        /*
                给定一个数有序数组，代表每个人喝完咖啡准备刷杯子的时间
                只有一台咖啡机，-次只能洗1个杯子，时间耗费a，洗完才能洗下一杯
                每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
                返回让所有咖啡杯变干净的最早完成时间
                三个参数: int[ arr、 inta、 int b|;

         */

        int[] arr = {1,3,5,10,10,10,12,12,12,13};
        int a = 3; //手洗时间
        int b = 10; //自动挥发时间
        System.out.println(minTimeOfWash1(arr, a, b)); //递归
        System.out.println(minTimeOfWash2(arr, a, b)); //动态规划
    }

    public static int minTimeOfWash1(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0 || a == 0 || b == 0) {
            return 0;
        }
        return process(arr, a, b, 0, 0); //
    }

    public static int process(int[] arr, int a, int b, int index, int washLine) { //washLine，手洗碗结束的时间
        if (index == arr.length - 1) { //还剩下最后一个杯子的时候
            return Math.min(Math.max(washLine, arr[index]) + a,  arr[index] + b); //手洗与自然挥发取最小值
        }

        //手洗的时候
        int wash = Math.max(arr[index], washLine) + a;
        int next1 = process(arr, a, b, index + 1, wash); //手洗的时间作为参数进行传递
        int p1 = Math.max(wash, next1); //手洗的最终时间，取最晚的时间
        //自动挥发的时候
        int volatilization = arr[index] + b;
        int next2 = process(arr, a, b, index + 1, washLine); //这里手洗的时间还是不变
        int p2 = Math.max(volatilization, next2); //自动挥发的最终时间，取最晚的时间

        return Math.min(p1, p2);
    }

    public static int minTimeOfWash2(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0 || a == 0 || b == 0) {
            return 0;
        } else if (a >= b) {
            return arr[arr.length - 1] + b;
        }
        //动态规划的形式
        /*
                难点就在于怎么改成缓存的形式，一个参数是index，另一个参数是washLine
                washLine的取值范围是多少？
                当然是全部都是手洗的情况咯
         */
        int N = arr.length;
        int limit = 0; //washLine的范围
        for (int i : arr) {
            limit = Math.max(limit, i) + a; //取二者的最大值再进行加法
        }
        //数组初始化
        int[][] dp = new int[N][limit + 1];
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(Math.max(arr[N - 1], washLine) + a, arr[N - 1] + b);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                //dp[index][washLine] = ?;
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(arr[index], washLine) + a; //手洗
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }

                int volatilization = arr[index] + b;
                int p2 = Math.max(volatilization, dp[index + 1][washLine]);

                dp[index][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

}
