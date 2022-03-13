import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-08
 * Time: 9:38
 * Description:笔试强训 14天 第二题 计算幸运的袋子
 */
public class CalcLuckyBag {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(calcLuckyBag(arr, 0, 0, 1));
    }

    public static int calcLuckyBag(int[] arr, int index, int sum, int mul) {
        int res = 0;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];
            mul *= arr[i];
            if (sum > mul) {
                res += 1 + calcLuckyBag(arr, i + 1, sum, mul);
            } else if (arr[i] == 1) { // 1 的话，能够提高总和，不能提高乘积
                res += calcLuckyBag(arr, i + 1, sum, mul);
            } else { // 后续的数都是最大的，当前都不可能呢，后面乘积更大
                break;
            }
            sum -= arr[i];
            mul /= arr[i];
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                i++;
            }
        }
        return res;
    }

}
