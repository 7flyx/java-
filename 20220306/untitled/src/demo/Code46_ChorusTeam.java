package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-22
 * Time: 20:42
 * Description: 4月22号 第一个代码题 合唱团
 * 第一个参数代表 人数；
 * 第二行是每个人的能力值；
 * 第三行第一个参数是 想要的人数，第二个参数是最远距离
 * https://www.nowcoder.com/questionTerminal/661c49118ca241909add3a11c96408c8
 */
public class Code46_ChorusTeam {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] power = new int[n];
        for (int i = 0; i < n; i++) {
            power[i] = sc.nextInt();
        }
        int person = sc.nextInt();
        int distance = sc.nextInt();
        System.out.println(calcMaxPower2(power, person, distance));
    }

    // 递归版本
    public static int calcMaxPower(int[] power, int person, int distance) {
        if (person == 0 || power == null || distance < 1) {
            return 0;
        }

        return process(power, person, distance, 0);
    }

    public static int process(int[] power, int person, int distance, int index) {
        if (index == power.length || person == 0) {
            return 0;
        }

        int res = 1;

        for (int i = index + 1; i < power.length && i <= index + distance; i++) {
            res = Math.max(res, process(power, person - 1, distance, i)); // 递归后续过程
        }
        return res * power[index]; // 返回后续最优结果+当前的值
    }

    public static long calcMaxPower2(int[] power, int person, int distance) {
        if (person == 0 || power == null || distance < 1) {
            return 0;
        }
        // 根据递归版本可知，可变参数只有两个 index和person
        int n = power.length;
        // 因为数据有正有负，所以需要维护两张表
        long[][] dpMax = new long[person + 1][n + 1];
        long[][] dpMin = new long[person + 1][n + 1];
        // 第一行全是0
        // 最右一列全是0
        long res = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) { // index，也就是列
            dpMax[1][i] = power[i - 1];
            dpMin[1][i] = power[i - 1];
            for (int k = 2; k <= person; k++) { // person ，也就是行
                for (int j = i - 1; j > 0 && i - j <= distance; j--) {
                    dpMax[k][i] = Math.max(dpMax[k][i],
                            Math.max(power[i - 1] * dpMax[k - 1][j], power[i - 1] * dpMin[k - 1][j]));
                    dpMin[k][i] = Math.min(dpMin[k][i],
                            Math.min(power[i - 1]  * dpMax[k - 1][j], power[i - 1]  * dpMin[k - 1][j]));
                }
            }
            res = Math.max(res, dpMax[person][i]);
        }
        return res;
    }

}
