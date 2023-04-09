package class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-02-16
 * Time: 18:50
 * Description:
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 */
public class Code04_Drive {
    public static void main1(String[] args) {
        System.out.println("test begin.");
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            int range = 100;
            int length = 20;
            int[][] income = generateIncome(range, length);
            int ans1 = maxMoney1(income);
            int ans2 = maxMoney1(income);
            if (ans1 != ans2) {
                System.out.println("test failed.");
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans2);
                break;
            }
        }
        System.out.println("test finished");
    }

    public static int[][] generateIncome(int range, int length) {
        if ((length & 1) == 1) {
            return new int[][]{};
        }
        int[][] income = new int[length][2];
        for (int i = 0; i < length; i++) {
            income[i][0] = (int) (Math.random() * range);
            income[i][1] = (int) (Math.random() * range);
        }
        return income;
    }


    // income.length 一定要是偶数
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) == 1) {
            return 0;
        }
        int N = income.length;
        return process(income, 0, N / 2);
    }

    // income[i][0] 指 i号司机去A区域的收入，income[i][1]指 i号司机去B区域的收入
    // 0~index-1号司机已经分配好了，剩下的index... 如何分配，使得AB区域人数一样，且整体收入最大化
    // rest：指 去A区域还剩多少名额
    private static int process(int[][] income, int index, int rest) {
        if (index == income.length) { // base case
            return 0;
        }

        // 只能去A区域
        if (income.length - index == rest) { // 还剩下没分配的司机，刚好去A区域还有rest个，即全部分配到A区域
            return income[index][0] + process(income, index + 1, rest - 1);
        }
        // 只能去B区域
        if (rest == 0) {
            return income[index][1] + process(income, index + 1, rest);
        }
        // 既能去A，也能去B，二者取最优结果
        int p1 = income[index][0] + process(income, index + 1, rest - 1);
        int p2 = income[index][1] + process(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    //动态规划版本
    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) == 1) {
            return 0;
        }

        // 根据暴力递归可得，可变参数有两个：index、rest
        int N = income.length;
        int rest = N / 2;
        // dp[i][j] 表示 从i及其往后全部司机，能去A区域的还有j个名额，返回这种情况的收入最大化。
        int[][] dp = new int[N + 1][rest + 1];
        // dp[N][...] = 0，即最后一行都是0, base case
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= rest; j++) {
                if (N - i == j) { // 即还剩下的司机数量 刚好等于 去A区域的数量，则剩下的全部都去A区域
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) { // 去A区域的数量 = 0,说明剩下的都是要去区域的
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else { // 即可以去A，也可以去B
                    int p1 = income[i][0] + dp[i + 1][j - 1]; // 去A区域
                    int p2 = income[i][1] + dp[i + 1][j]; // 去B区域
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][rest];
    }

}