import java.io.*;
import java.util.Arrays;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-24
 * Time: 22:48
 * Description:给定一个矩阵matrix，其中的值有正、有负、有0，返回子矩阵的最大累加和
 */

public class SubRecSum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = in.readLine().split(" ");

        int N = Integer.parseInt(nums[0]);
        int M = Integer.parseInt(nums[1]);
        int[][] arr = new int[N][M];
        for (int i = 0; i <N; i++) {
            nums = in.readLine().split(" ");
            for (int j = 0; j <M; j++) {
                arr[i][j] = Integer.parseInt(nums[j]);
            }
        }

        //进行计算
        System.out.println(calcSubRec(arr));
        in.close();
    }

    public static int calcSubRec(int[][] arr) {
        if (arr == null || arr.length == 0 ) {
            return 0;
        }

        int N = arr.length;
        int M = arr[0].length;
        int[] tmp = {};
        int max = Integer.MIN_VALUE;
        //每一行作为起点，向下组合求结果
        for (int i = 0; i < N; i++) {
            tmp = Arrays.copyOf(arr[i], M);
            for (int j = i; j < N; j++) {
                if (j != i) { //数组两行之间做叠加
                    arrayAddOfRow(arr, tmp, j);
                }
                max = Math.max(max, calcSubArray(tmp));
            }
        }
        return max;
    }

    public static void arrayAddOfRow(int[][] arr, int[] tmp, int index) {
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] += arr[index][i];
        }
    }

    public static int calcSubArray(int[] tmp) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tmp.length; i++){
            sum += tmp[i];
            max = Math.max(max, sum);
            sum = sum < 0? 0 : sum;
        }
        return max;
    }
}
