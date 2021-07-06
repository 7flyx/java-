package demo1;

public class demo2 {
    public static void main(String[] args) {
        //动态规划
        /*
                给定两个长度都为N的数组weights和values,
                weights[i]和values[i]分别代表i号物品的重量和价值。
                给定一个正数bag，表示一-个载重bag的袋子，
                你装的物品不能超过这个重量。
                返回你能装下最多的价值是多少？
         */
        int[] W = {10,20,9,50};
        int[] V = {20,10,30,40};
        System.out.println(num1(W, V,40));
        System.out.println(num2(W, V,40));

    }

    public static int num1(int[] W, int[] V, int bag) {
        if (W == null || V == null || bag <= 0) {
            return 0;
        }

        //暴力递归
        return process1(W, V, 0, bag);
    }

    public static int process1(int[] W, int[] V, int index, int rest) {
        if (rest < 0) {
            return -1; //说明这种方案不可取
        }

        //rest >= 0 的情况
        if (index == W.length) {
            return 0; //已经没有物品了
        }

        //分情况，当前这个位置的物品，要与不要，两种情况
        int no = process1(W, V, index + 1, rest) ;
        int yes = process1(W, V, index + 1, rest - W[index]);
        if (yes != -1) {
            yes += V[index]; //加上当前这个位置的价值数
        }
        return Math.max(no, yes);
    }

    public static int num2(int[] W, int[] V, int bag) {
        if (W == null || V == null || bag <= 0) {
            return 0;
        }
        //根据上面的暴力递归分析，该函数有两个可变参数（index， rest），所以这里需要一个二维数组进行缓存
        //记忆化搜索
        int N = W.length;
        int[][] res = new int[N + 1][bag + 1];
        //res[N][i] = 0; 数组的最后一行为0，java默认是0，不需要初始化
        for (int index = N - 1; index >= 0; index--) { //行数
            for (int rest = 0; rest <= bag; rest++) { //列数，是背包的容量
                int no = res[index + 1][rest];
                int yes = -1;
                if (rest - W[index] >= 0) {
                    yes = res[index + 1][rest - W[index]] + V[index];
                }
                res[index][rest] = Math.max(no, yes);
            }
        }
        return res[0][bag];
    }
}
