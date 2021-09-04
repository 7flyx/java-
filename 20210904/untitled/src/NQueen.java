/**
 * Created by flyx
 * Description: N皇后问题
 * User: 听风
 * Date: 2021-09-04
 * Time: 10:01
 */
public class NQueen {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //ms
        System.out.println(getNumQueen1(14));
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗 " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        System.out.println(getNumQueen2(14));
        endTime = System.currentTimeMillis();
        System.out.println("共消耗 " + (endTime - startTime) + "ms");
    }

    //常规解法：时间复杂度为  N的n次方。 以行为单位，一行放一个，就进入下一行，
    //判断不共行、不共列、不共斜线
    public static int getNumQueen1(int n) {
        if (n == 1) {
            return 1;
        }
        if (n < 4) {
            return 0;
        }
        int[] record = new int[n]; //存储皇后的位置。下标为行数，里面的元素为列数。
        return process1(record, 0, n); //行数
    }

    private static int process1(int[] record, int i, int n) {
        if (i == n) {
            return 1; //base case 。已经来到了最后一行的下面
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isViolation(record, i, j)) { //判断是否合法
                record[i] = j;
                res += process1(record, i + 1, n);
            }
        }
        return res;
    }

    private static boolean isViolation(int[] record, int i, int j) {
        //判断0~i-1行的皇后，与i行的皇后是否冲突
        for (int k = 0; k < i; k++) {
            //不共列、不共斜线
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    public static int getNumQueen2(int n) {
        if (n ==1) {
            return 1;
        }
        if (n < 4) {
            return 0;
        }

        //不要超过32皇后
        int limit = n == 32? -1 : (1 << n) - 1; //后面n位全是1
        return process2(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit 所有皇后的位置， 0表示可以放，1表示不可以放
     * @param corLim 当前这一行的状态， 0表示可以放，1表示不可以放
     * @param leftLineLim 左斜线的状态，表示corLim左移一位
     * @param rightLineLim 右斜线的状态， 表示corLim右移一位
     * @return 放回皇后的总摆法
     */
    private static int process2(int limit,
                                int corLim,
                                int leftLineLim,
                                int rightLineLim) {
        if (limit == corLim) { //所有的皇后都摆放好了
            return 1;
        }

        //原本是0可以放，这里取反后，pos的状态就是 1可以放，便于后面取出每一个1的位置
        //与limit与运算，是为了，除出corLim二进制中，左侧的1
        int pos = limit & (~(corLim | leftLineLim | rightLineLim));
        int mossRightOne = 0; //用于取出pos，二进制中最靠右的1
        int res = 0;
        while (pos != 0) {
            mossRightOne = pos & (~pos + 1); //取出最靠右的1
            pos = pos ^ mossRightOne; //减去最靠右的1
            res += process2(limit,
                    (corLim | mossRightOne),
                    (leftLineLim | mossRightOne) << 1, //左移一位
                    (rightLineLim | mossRightOne) >>> 1); //右移一位
        }
        return res;
    }
}
