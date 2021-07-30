package demo1;


import java.util.Scanner;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-07-30
 * Time: 8:53
 */
public class demo1 {

    public static void main(String[] args) {
        //递归求n的阶乘
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(calclateFac(n));
    }

    public static int calclateFac(int n) {
        if (n == 1) {
            return 1;
        }
        return n * calclateFac(n - 1);
    }

    public static void main13(String[] args) {
        //递归求和
        int n = 10;
        System.out.println(calcSumOfNum(n));
    }

    public static int calcSumOfNum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + calcSumOfNum(n - 1);
    }

    public static void main12(String[] args) {
        //按顺序打印一个数字的每一位(例如 1234 打印出 1 2 3 4) （递归）
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        printNumOfEach(num);
    }

    public static void printNumOfEach(int num) {
        if(num < 10) {
            System.out.print(num + " ");
            return;
        }
        printNumOfEach(num / 10);
        System.out.print(num % 10 + " ");
    }

    public static void main11(String[] args) throws RuntimeException {
        //写一个递归方法，输入一个非负整数，返回组成它的数字之和
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(calcFromOfNum(num));
    }

    public static int calcFromOfNum(int num) throws RuntimeException {
        if (num < 0) {
            throw new RuntimeException("It can't be negative");
        }
        if (num < 10) {
            return num;
        }
        return num % 10 + calcFromOfNum(num / 10);
    }

    public static void main10(String[] args) {
        //递归求斐波那契数第n项
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fbi(n));

    }

    public static int fbi(int n) {
        if (n < 3) { //base case
            return 1;
        }
        return fbi(n - 1)  + fbi(n - 2);
    }

    public static void main9(String[] args) {
        //汉诺塔
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //n 层汉诺塔
        hnoi(n, "left", "right", "mid");
    }

    public static void hnoi(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println(from + " -> " + to);
            return;
        }
        hnoi(n - 1, from, other, to); // n - 1层，先到中间，让出路
        System.out.println(from + " -> " + to); //路让出来后，n直接到to位置
        hnoi(n - 1, other, to, from); //n - 1层，再从other处，往to走
    }

    public static void main8(String[] args) {
        //青蛙跳台阶问题
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(fronDanceSteps(n));
    }

    public static int fronDanceSteps(int n) {
        if (n < 3) { //base case
            return n;
        }
        return fronDanceSteps(n - 1)  + fronDanceSteps(n - 2);
    }

    public static void main7(String[] args) {
        //在同一个类中,分别定义求两个整数的方法 和 三个小数之和的方法。 并执行代码，求出结果
        System.out.println(sum(10, 20));
        System.out.println(sum(10.0, 20.0, 30.0));

    }

    public static int sum(int num1, int num2) {
        return num1 + num2;
    }
    public static double sum(double num1, double num2, double num3) {
        return num1 + num2 + num3;
    }

    public static void main6(String[] args) {
        // 在同一个类中定义多个方法：要求不仅可以求两个整数的最大值，还可以求两个小数的最大值，以及两个小数和一个整数的大小关系
        System.out.println(MAX(10, 20));
        System.out.println(MAX(10.0, 20.0));
        System.out.println(MAX(10.0, 20.0, 50));
    }

    public static int MAX(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }

    public static double MAX(double num1, double num2) {
        return num1 > num2 ? num1 : num2;
    }

    public static double MAX(double num1, double num2, int num3) {
        double max = num1 > num2 ? num1 : num2;
        return max > num3 ? max : num3;
    }

    public static void main5(String[] args) {
        //创建方法求两个数的最大值max2，随后再写一个求3个数的最大值的函数max3。
        //​ 要求：在max3这个函数中，调用max2函数，来实现3个数的最大值计算

        int num1 = 10;
        int num2 = 20;
        int num3 = 30;
        System.out.println(myMaxNum2(num1, num2));
        System.out.println(myMaxNum3(num1, num2, num3));

    }

    public static int myMaxNum2(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }

    public static int myMaxNum3(int num1, int num2, int num3) {
        int max = myMaxNum2(num1, num2);
        return max > num3 ? max : num3;
    }

    public static void main4(String[] args) {
        //调整数组顺序使得奇数位于偶数之前。调整之后，不关心大小顺序。
        int[] arr = {2, 6, 4, 8, 10, 12, 15, 1, 3, 5, 7, 9, 11, 15, 19, 21};
        adjustOdd_Even(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void adjustOdd_Even(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            left = (arr[left] & 1) == 0 ? left : left + 1; //找到左边的偶数
            right = (arr[right] & 1) == 0 ? right - 1 : right; //找到右边的奇数
            if ((arr[left] & 1) == 0 && (arr[right] & 1) != 0) {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                right--;
                left++;
            }
        }
    }

    public static void main3(String[] args) {
        //求阶乘的和
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 1;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i <= n; i++) {
            sum1 += calclateFac(i);//递归的形式
            res *= i;
            sum2 += res;
        }
        System.out.println(sum1);
        System.out.println(sum2);
    }



    public static void main2(String[] args) {
        //斐波那契数列
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 3) {
            System.out.println("1");
        }

        int pre = 1;
        int cur = 1;
        int next = 0;
        for (int i = 3; i <= n; i++) {
            next = pre + cur;
            pre = cur;
            cur = next;
        }
        System.out.println(cur);
    }

    public static void main1(String[] args) {
        //有一组数据，只有一个数字是出现一次，其他是两次，请找出这个数字。
        int n = (int) (Math.random() * 100);
        n = (n & 1) == 0 ? n + 1 : n; //奇数

        int[] arr = new int[n];
        for (int i = 0; i < n / 2; i++) {
            arr[i] = (int) (Math.random() * 100) + 1; //1-100之间的随机数
            arr[i + n / 2] = arr[i];
        }
        arr[n - 1] = (int) (Math.random() * 100) + 100;

        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= arr[i];
            System.out.println(arr[i]);
        }
        System.out.println("res = " + res);
    }
}
