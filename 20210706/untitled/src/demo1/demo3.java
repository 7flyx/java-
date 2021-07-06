package demo1;

public class demo3 {
    public static void main(String[] args) {
        //动态规划
         /*
                给定一个整型数组arr，代表数值不同的纸牌排成一条线,
                玩家A和玩家B依次拿走每张纸牌,
                规定玩家A先拿，玩家B后拿，
                但是每个玩家每次只能拿走最左或最右的纸牌,
                玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。

          */

        int[] arr = {10, 50, 30, 20};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int L, int R) { //先手，肯定是拿两边最大的
        if (L == R) {
            return arr[L];
        }
        return Math.max(s(arr, L + 1, R) + arr[L],
                s(arr, L, R - 1) + arr[R]);
    }

    public static int s(int[] arr, int L, int R) { //后手的话，肯定是拿先手之后的，所以就是取最小的
        if (L == R) {
            return 0; //后手拿，本来数组就只剩了一个，还是后手拿，肯定就是0了
        }
        return Math.min(f(arr, L + 1, R),
                f(arr, L, R - 1));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //在暴力递归中，有两个函数f与s在互相调用，且每个函数里面有两个可变参数L与R，所以建两个二维数组即可
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i]; //先手，当LR相等时，取其一即可
        }
        //s[i][i] = 0; 默认初始化就是0，所以不管
        for (int i = 1; i < N; i++) {
            int L = 0;  //行
            int R = i; //列
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R],  arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                L++;
                R++;
            }
        }
      return Math.max(f[0][N - 1], s[0][N - 1]);
    }
}
