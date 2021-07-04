package demo1;

import java.util.Scanner;

public class demo1 {
    public static void main(String[] args) {
        //N皇后问题

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入n个皇后:");
        int n = sc.nextInt();
//        long start = System.currentTimeMillis();
//        int sum = num1(n);
//        long end = System.currentTimeMillis();
//        System.out.println(sum);
//        System.out.println("共消耗时间为 " + (end - start) + "ms");

        long start = System.currentTimeMillis();
        long sum = num2(n);
        long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("共消耗时间为 " + (end - start) + "ms");

    }

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }

        int[] record = new int[n]; //下标表示 x值，  record[i]  表示 y值。 表示已经放入皇后的坐标
        return process1(n, record, 0); //i表示当前行
    }

    public static int process1(int n, int[] record, int i) {
        if (i == n) {
            return 1; //说明皇后已经放完了，返回当前这种状态，即就是一种方案
        }

        int res = 0; //实则这个收集的这一条分支下， 所有的结果
        for (int k = 0; k < n; k++) { //遍历这一行的所有位置
            if (isExist(record, i, k)) {  //i表示行， k表示列
                record[i] = k;
                res += process1(n, record, i + 1); //转到下一行
            }
        }
        return res;
    }

    public static boolean isExist(int[] record, int x, int y) {
        for (int k = 0; k < x; k++) { //循环遍历到x行即可
            if (y == record[k] || Math.abs(x - k) == Math.abs(y - record[k])) {
                return false;
            }
        }
        return true;
    }

    //我们以一个int中的每一位作为皇后的位置是，所以不能超过32位，但是现在的电脑极限就在15位左右
    public static int num2(int n) {
        if (n < 1 || n > 16) {
            return 0;
        }

        int limit = (1 << n) - 1; //将后面的位全部变为1，前面的位置上还是0
        return process2(limit, 0, 0, 0); // 列限制， 左斜线限制， 右斜线限制
    }

    public static int process2(int limit, int colLim, int leftLim, int rightLim) {
        if (colLim == limit) {
            return 1; //colLim相当于  行数
        }

        //将三个限制的结果或运算后，取反，再做与运算，得到当前这一行，哪些位置上可以放皇后
        int pos = limit & (~(colLim | leftLim | rightLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1); //拿到这个数最右边的1
            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    (leftLim | mostRightOne) << 1,
                    (rightLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
