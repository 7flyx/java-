package demo1;

public class demo4 {
    public static void main(String[] args) {
        //动态规划
        /*
                假设有排成一行的N个位置，记为1~N, N-定大于或等于2
                开始时机器人在其中的M位置.上(M-定是1~N中的一个)
                如果机器人来到1位置，那么下一步只能往右来到2位置;
                如果机器人来到N位置，那么下一步只能往左来到N-1位置;
                如果机器人来到中间位置，那么下一步可以往左走或者往右走;
                规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
                给定四个参数N、M、K、P,返回方法数。
         */
        int N = 7;
        int M = 2;
        int K = 30;
        int P = 6;
        System.out.println(num1(N, M, K, P));
        System.out.println(num2(N, M, K, P));
    }

    public static int num1(int N, int M, int K, int P) {
        if (N < 1 || M < 1 || K < 1 || P < 1 || P > N) {
            return 0;
        }
        //N路程范围， M 起点， K 剩余步数， P 终点
        return process1(N, M, K, P);
    }

    public static int process1(int N, int start, int surplus, int P) {
        if (surplus == 0) {
            return start == P ? 1 : 0;
        }
        //surplus还有剩余的步数，判断当前所处的位置，进行下一步的判断
        if (start == 1) {
            return process1(N, start + 1, surplus - 1, P);
        }
        if (start == N) {
            return process1(N, start - 1, surplus - 1, P);
        }
        return process1(N, start + 1, surplus - 1, P) + process1(N, start - 1, surplus - 1, P);
    }

    public static int num2(int N, int start, int surplus, int P) {
        if (N < 1 || start < 1 || surplus < 1 || P < 1 || P > N) {
            return 0;
        }
        //由上面的暴力递归函数，可变参数两个：start和surplus，所以建立一个二维数组即可
        int[][] res = new int[surplus + 1][N + 1];
        res[0][P] = 1; //剩余步数为0.且是终点，就返回1
        for (int i = 1; i <= surplus; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    res[i][j] = res[i - 1][j + 1]; //往右边走
                } else if (j == N) {
                    res[i][j] = res[i - 1][j - 1]; //往左边走
                } else {
                    res[i][j] = res[i - 1][j + 1] + res[i - 1][j - 1]; //左右两边都可以走
                }
            }
        }
        return res[surplus][start];
    }
}
