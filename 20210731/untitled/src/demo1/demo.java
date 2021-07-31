package demo1;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-07-31
 * Time: 9:32
 */
public class demo {

    public static void main(String[] args) {
        int n = 5; //假设就是3层台阶
        System.out.println(frogJumpPlus(n));
    }

    public static int frogJumpPlus(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i] += dp[j];
            }
        }

        return dp[n];
    }

    public static int frogJumpPlus1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int res = 1; //当前，需要选择一种的跳法

        //此处的for循环，就是去尝试，跳1步、2步、3步……一直跳到n层台阶，将这些情况全部加起来，就是当前的结果
        for (int i = 1; i < n; i++) {
            res += frogJumpPlus(n - i); //i控制台阶数，每次循环，都减一步台阶。去递归所有的台阶
        }

        return res;

    }

    public static void main2(String[] args) {
        //汉诺塔
        int n = 3;
        //hanoiTower(n, "left", "right", "mid");
        int[] arr = {1,2,3,4,5};
        System.out.println(myToString(arr));
    }
    public static String myToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                sb.append(array[i]).append(", ");
            } else {
                sb.append(array[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }//[1, 2, 3, 4, 5, 6]

    public static void hanoiTower(int n, String from, String to, String mid) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }
        hanoiTower(n - 1, from, mid, to);
        System.out.println("move " + n + " from " + from + " to " + to);
        hanoiTower(n - 1, mid, to, from);
    }

    public static void main1(String[] args) {
        double a = 10.0;
        double b = 20.0;
        double c = add(a, b);
        int d = add(10, 20);
        System.out.println(c);
        System.out.println(d);
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static double add (double num1, double num2) {
        return num1 + num2;
    }

}

