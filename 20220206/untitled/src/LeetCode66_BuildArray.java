import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-23
 * Time: 16:23
 * Description:剑指offer 66 构建乘积数组
 */
public class LeetCode66_BuildArray {
    class Solution {
        //时间O(N)，空间O(N)
        public int[] constructArr1(int[] a) {
            if (a == null || a.length == 0) {
                return new int[]{};
            }
            int[] res = new int[a.length];
            int[] rightNum = new int[a.length + 1];
            rightNum[a.length - 1] = a[a.length - 1];
            rightNum[a.length] = 1;
            for (int i = a.length - 2; i >= 0; i--) {
                rightNum[i] = a[i] * rightNum[i + 1];
            }

            int pre = 1;
            for (int i = 0; i < a.length; i++) {
                res[i] = pre * rightNum[i + 1];
                pre *= a[i];
            }
            return res;
        }

        //时间O(N)，空间O(1)
        public int[] constructArr(int[] a) {
            if (a == null || a.length == 0) {
                return new int[]{};
            }
            int N = a.length;
            int[] res = new int[N];
            int tmp = 1;
            res[0] = 1;
            for (int i = 1; i < N; i++) {
                //将 i-1范围的乘积，放入i位置
                res[i] = res[i - 1] * a[i - 1];
            }
            //从倒数第二个位置开始计算
            for (int i = N - 2; i >= 0; i--) {
                tmp *= a[i + 1];//拿取i右边的乘积
                res[i] = tmp * res[i]; //res[i]最开始是表示i-1范围的乘积
            }
            return res;
        }
    }

    public static void main(String[] args) {

    }
}
