/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-12
 * Time: 21:45
 * Description:
 * 有1分，2分，5分，10分四种硬币，每种硬币数量无限，给定n分钱(n <= 100000)，有多少中组合可以组成n分钱
 */
public class Demo {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(moneyCombine(n));
    }

    public static int moneyCombine(int n) {
        if (n < 0) {
            return 0;
        }
        return process(n, 0);
    }

    public static int process(int n, int cur) {
        if (cur > n) {
            return 0;
        }
        if (cur == n) {
            return 1;
        }

        int res = 0;
        for (int i = cur + 1; i <= n; i += 1) {
            res += process(n, i);
        }
        for (int i = cur + 2; i <= n; i += 2) {
            res += process(n, i);
        }
        for (int i = cur + 4; i <= n; i += 4) {
            res += process(n, i);
        }
        return res;
    }
}
