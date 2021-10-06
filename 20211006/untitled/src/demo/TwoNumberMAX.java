package demo;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-06
 * Time: 16:51
 * Description: 给定两个32位的整数，不用比较，返回较大值。
 */
public class TwoNumberMAX {
    public static void main(String[] args) {
        int a = 20;
        int b = 30;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));

        System.out.println("==========");
        a = Integer.MAX_VALUE;
        b = -2;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
    }

    //方法1，可能溢出
    public static int getMax1(int a, int b) {
        int c = a - b; //差值
        int scA = sign(c);  //正数返回1，负数返回0
        int scB = flip(scA); //与scA，取相反值
        return scA * a + scB * b; //scA 和 scB，肯定一个是1，一个是0
    }

    /**
     *  如果形参数1，则返回0.如果形参数是0，则返回1
     * @param n 形参
     * @return 返回 0或1
     */
    private static int flip(int n) {
        return n ^ 1;
    }

    private static int sign(int n) {
        return flip((n >> 31) & 1); //拿到符号位
    }

    public static int getMax2(int a, int b) {
        int c = a - b;
        int scA = sign(a);
        int scB = sign(b);
        int scC = sign(c); //分别计算这3个数的符号位，看是否是正数
        int difSab = scA ^ scB; //相同符号，为0
        int sameSab = flip(difSab); //取相反

        //如果scA和scB，是相同符号的话，就不会溢出。此时我们就回到了上面方法一的判断
        //如果不同号的话，可能会产生溢出。此时我们只需判断其中一个数的正负号，肯定是一个正数，一个负数。
        //下面这一句returnA，是整个代码的核心部分。用加号，连接了两种情况。达到了if else的效果
        int returnA = sameSab * scC + difSab * scA;
        int returnB = flip(returnA);
        return returnA * a + returnB * b; //returnA和returnB，一个是0，一个是1
    }
}
