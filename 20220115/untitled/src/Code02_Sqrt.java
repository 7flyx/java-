/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 9:14
 * Description: 实现sqrt函数
 */
public class Code02_Sqrt {
    public int sqrt1 (int x) {
        if (x <= 0) {
            return 0;
        }

        //只有是>=0的数
        int res = 0;
        for (int i = 1; i * i <= x; i++) {
            if (i * i <= x) {
                res = i;
            }
        }
        return res;
    }

    public int sqrt (int x) {
        if (x <= 0) {
            return 0;
        }

        //只有是>=0的数
        int l = 1;
        int r = x;
        int mid = 0;
        while (true) {
            mid = l + ((r - l) >> 1);
            if (mid <= x / mid && (mid + 1) > x / (mid + 1) ) {
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
