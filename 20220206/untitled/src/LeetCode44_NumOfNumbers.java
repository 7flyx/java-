/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-16
 * Time: 19:32
 * Description: 剑指offer44 数字序列中的某一位数字
 */
public class LeetCode44_NumOfNumbers {
    public static int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(100));
    }
}
