import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-13
 * Time: 13:26
 * Description:
 */
public class Demo {

    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        System.out.println(f2(N));
    }
    public static int f2(int N) {
        if (N <= 1) {
            return 1;
        }
        int res = 0;
        int L = 0; //第一层
        int R = N - 1; //最后一层
        int mid = 0;
        while (L <= R && res == 0) { //res=0，说明还没有计算出来结果
            mid = (L + R) / 2; //取中间值
            int num = 1;
            int len = (mid & 1) == 0? mid / 2 : mid / 2 + 1;
            for (int j = 0; j <= len; j++) {
                num = num * (mid - j) / (j + 1);
                if (num == N) {
                    res = j + 1;
                    break;
                }
            }
            if (num < N) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        //计算前面的层数
        res += (1 + mid) * mid / 2 ;//等差数列的求和
        return res + 1;
    }

    public static int f(int N) {
        if (N <= 1) {
            return 1;
        }
        int[] cur = new int[1];
        int[] pre = new int[1];
        pre[0] = 1;
        int res = 1;
        boolean isFind = false;
        for (int level = 1; !isFind ; level++) { //层数
            if ((level & 1) == 0) { //偶数层
                cur = new int[level / 2];
            } else { //奇数层
                cur = new int[level / 2 + 1];
            }
            cur[0] = 1;
            for (int i = 1; i < cur.length; i++) {
                cur[i] = i >= pre.length? 2 * pre[i - 1] : pre[i - 1] + pre[i];
                if (cur[i] == N) {
                    res += i;
                    isFind = true;
                    break;
                }
            }
            pre = cur;
            if (!isFind) {
                res += level;
            }
        }
        return res;
    }

    public static void main1(String[] args) {
        String s = "hello, world\0";
        System.out.println(s);
        System.out.println(sqrt(1));


        int a = 13;
        a = a / 5;
        System.out.println(a);

        int i = -5;
        //i = ++(i++)  ; //编译错误，自增自减后是一个常量，不能对一个常量进行自增自减
        System.out.println(i);
    }

    public static int sqrt(int x) {
        if (x <= 0) {
            return 0;
        }

        //只有是>=0的数
        int l = 1;
        int r = x;
        int mid = 0;
        while (true) {
            mid = l + ((r - l) >> 1);
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                break;
            } else if (mid < x / mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return mid;
    }
}
