package class01;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:11
 * Description: 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
 */
public class Code03_Near2Power {
    public static void main(String[] args) {
        System.out.println(near2Power(10));
    }

    public static int near2Power(int num) {
        if (num <= 0) {
            return num;
        }
        // 原理：将num此时最高位的二进制1，往后进行填充，将尾部全部变为1之后，+1，往前凑2的某次方
        num -= 1; // 防止的是num本身就是一个2的某次方的数
        num |= (num >>> 1);
        num |= (num >>> 2);
        num |= (num >>> 4);
        num |= (num >>> 8);
        num |= (num >>> 16); // 总共32位数，左移16就是左移了一半了
        return num + 1; // +1的原因是，上诉代码是将该数的后面全变为二进制1，+1的话就能凑出一个二进制1往前
    }
}
