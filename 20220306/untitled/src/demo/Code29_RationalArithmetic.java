package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-02
 * Time: 11:10
 * Description: 4月2号 第一个代码题 简单点说，就是给你两个分数，计算这两个分数四则运算的结果，这个结果是有一定格式的
 */
public class Code29_RationalArithmetic {
    private static class Fraction {
        public long up; // 分子
        public long down; // 分母

        public Fraction(long up, long down) {
            this.up = up;
            this.down = down;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" "); // 以空格隔开，就是两个分数
        String[] num1 = arr[0].split("/");
        String[] num2 = arr[1].split("/");

        Fraction a = new Fraction(Integer.parseInt(num1[0]), Integer.parseInt(num1[1]));
        Fraction b = new Fraction(Integer.parseInt(num2[0]), Integer.parseInt(num2[1]));

        StringBuilder sb = new StringBuilder();
        // 加减运算
        sb.append(process(a)).append(" + ");
        sb.append(process(b)).append(" = ");
        sb.append(process(add(a, b))).append("<br/>");
        // 减法
        sb.append(process(a)).append(" - ");
        sb.append(process(b)).append(" = ");
        sb.append(process(minus(a, b))).append("\n"); // 换行

        // 乘法
        sb.append(process(a)).append(" * ");
        sb.append(process(b)).append(" = ");
        sb.append(process(mul(a, b))).append("<br/>");
        // 除法
        sb.append(process(a)).append(" / ");
        sb.append(process(b)).append(" = ");
        if (b.up == 0) {
            sb.append("Inf\n");
        } else {
            sb.append(process(div(a, b)) + "\n");
        }
        System.out.println(sb.toString());
    }

    // 传入一个分数进来，计算结果并返回

    /**
     * @param num 分数
     * @return 结果，包括了异常情况
     */
    public static String process(Fraction num) {
        if (num == null) {
            return " ";
        }
        if (num.up == 0) { // 分子为0的情况，直接返回0
            return "0";
        }
        reduction(num);
        // 化简 抽离出能整除的部分
        StringBuilder sb = new StringBuilder();
        if (num.up < 0) { // 小于0的时候，要加括号
            sb.append("(");
        }
        if (num.down == 1) { // 整除的情况
            sb.append(num.up);
        } else if (Math.abs(num.up) > num.down) { // 分子大一点的情况
            sb.append(num.up / num.down + " ").append(num.up % num.down + "/" + num.down);
        } else {
            sb.append(num.up + "/" + num.down);
        }
        if (num.up < 0) {
            sb.append(")");
        }
        return sb.toString();
    }

    // 两个分数相加，返回一个新的分数
    public static Fraction add(Fraction a, Fraction b) {
        long up = a.up * b.down + b.up * a.down;
        long down = a.down * b.down;
        return new Fraction(up, down);
    }

    // 减法
    public static Fraction minus(Fraction a, Fraction b) {
        long up = a.up * b.down - b.up * a.down;
        long down = a.down * b.down;
        return new Fraction(up, down);
    }

    // 乘法
    public static Fraction mul(Fraction a, Fraction b) {
        long up = a.up * b.up;
        long down = a.down * b.down;
        return new Fraction(up, down);
    }

    // 除法---a * b的倒数
    public static Fraction div(Fraction a, Fraction b) {
        long up = a.up * b.down;
        long down = a.down * b.up;
        return new Fraction(up, down);
    }

    private static void reduction(Fraction num) {
        if (num.down < 0) { // 分母是负号，移动到分子上
            num.down = -num.down;
            num.up = -num.up;
        }
        if (num.up == 0) {
            num.down = 1;
        } else {
            long d = gcd(Math.abs(num.up), Math.abs(num.down)); // 计算最大公因数
            num.up /= d;
            num.down /= d; // 约分
        }
    }

    private static long gcd(long up, long down) {
        if (down == 0) {
            return up;
        }
        return gcd(down, up % down);
    }
}
