package demo;
import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-14
 * Time: 12:39
 * Description: 4月14号 第二个代码题 最长递增子序列的长度
 */
public class Code37_LargestAscendingSubStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println(process(arr));
        }
    }

    public static int process(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        // 下标表示长度，值表示该长度最后的一个值是多少
        // 例如：1下标表示 长度为1的子序列中，最后一个值是len[1]
        int[] len = new int[n];
        len[0] = arr[0];
        int range = 0; // 当前更新到的最长长度子序列
        for (int i = 1; i < n; i++) {
            int index = binarySearch(len, range, arr[i]);
            len[index] = arr[i]; // 更新len数组
            range = Math.max(index, range);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (len[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    public static int binarySearch(int[] len, int range, int num) {
        // 二分查找
        int l = 0;
        int r = range;
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (len[mid] >= num) { // 中间值 比 目标值要大或等于，R往左
                r = mid - 1;
            } else { // 中间值 比 目标值要小，L往右
                l = mid + 1;
            }
        }
        return l;
    }
}
