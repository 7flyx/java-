/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-17
 * Time: 19:54
 * Description: 剑指offer49 丑叔
 */
public class LeetCode49_UglyNumber {
    public static int nthUglyNumber(int n) {
        if (n < 7) {
            return n;
        }

        int count = 6; //前面已经有6个了
        int res = 0;
        for (int i = 7; count != n; i++) {
            int tmp = i;
            res = i;
            while (tmp % 2 == 0) {
                tmp /= 2;
            }
            while (tmp % 5 == 0) {
                tmp /= 5;
            }
            while (tmp % 3 == 0) {
                tmp /= 3;
            }
            if (tmp == 1) {
                count++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
