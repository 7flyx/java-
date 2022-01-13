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
        //int N = new Scanner(System.in).nextInt();
        System.out.println(f2(10));
    }

    public static int f2(int N) {
        if (N <= 1) {
            return 1;
        }
        int res = 1;
        for (int i = 0; i < N; i++) {
            int num = 1;
            int len = (i & 1) == 0? i / 2 : i / 2 + 1;
            for (int j = 0; j <= len; j++) {
                num = num * (i - j) / (j + 1);
                if (num == N) {
                    res += j + 1;
                    return res;
                }
            }
            res += i + 1;
        }
        return -1;
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
