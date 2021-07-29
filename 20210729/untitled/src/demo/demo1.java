package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by flyx
 * Description: 作业练习题
 * User: 听风
 * Date: 2021-07-29
 * Time: 9:41
 */
public class demo1 {

    public static int func(int n) {
        /*if (n > 10) {
            return n % 10 + func(n / 10);
        }
        return n;*/

        if (n < 10) { //base case
            return n;
        }
        return n % 10 + func(n / 10);
    }

    public static void main(String[] args) {

        String str1 = "hello";
        String str2 = "hello";
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));

        String str3 = "world";
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str3));

        int n = -1;
        System.out.println(n >> 1);
        System.out.println(n >>> 1); //这两个输出，有区别吗？

    }




    public static void main16(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(func1(n));
        System.out.println(func2(n));
    }

    public static boolean func1(int n) {
        int count = 0;
        while (n != 0) {
            int rightOne = n & (~n + 1);
            n -= rightOne;
            count++;
        }
        return count == 1;
    }

    public static boolean func2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static void main15(String[] args) {
        int a = 20;
        int b = 30;
        String s = "chongqing";

        String res1 = a + b + s;
        String res2 = s + a + b;
        System.out.println(res1);
        System.out.println(res2);
    }

    public static void main14(String[] args) {
        //输出乘法口诀表
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d * %d = %2d\t", j, i, i * j);
            }
            System.out.println();
        }
    }

    public static void main13(String[] args) {
        //输出一个数的每一位
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] str = String.valueOf(n).toCharArray();
        for (char s : str) {
            System.out.print(s + " ");
        }
    }

    public static void main12(String[] args) {
        //模拟密码登陆
        Scanner sc = new Scanner(System.in);
        String pass = "cool";
        for (int i = 0; i < 3; i++) {
            String tmp = sc.nextLine();
            if (pass.equals(tmp)) {
                System.out.println("登陆成功");
                System.exit(0);
            }
        }
        System.out.println("登陆失败");
    }

    public static void main11(String[] args) {
        //获取一个数二进制序列中所有的偶数位和奇数位， 分别输出二进制序列
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //example : 129  -> 1000 0001
        System.out.println(n & 0xaaaaaaaa); //偶数位
        System.out.println(n & 0x55555555); //奇数位
    }

    public static void main10(String[] args) {
        //计算一个数二进制中，1的个数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        while (n != 0) {
            int rightOne = n & (~n + 1); //拿到二进制最靠右的1
            n -= rightOne;
            count++;
        }
        System.out.println(count);
    }

    public static void main9(String[] args) {
        //求两个正整数的最大公约数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //保证两个都是奇数
        while ((n & 1) != 1) {
            n >>= 1;
        }
        while ((m & 1) != 1) {
            m >>= 1;
        }

        int[] arr = {m, n};
        //更相减损法
        while (true) {
            if (arr[0] < arr[1]) { //保证第一个数最大
                swap(arr);
            }
            int num = arr[0] - arr[1];
            if (num == arr[1]) {
                System.out.println(num);
                break;
            }
            arr[0] = num;
        }
    }

    public static void swap(int[] arr) {
        int tmp = arr[0];
        arr[0] = arr[1];
        arr[1] = tmp;
    }

    public static void main8(String[] args) {
        //计算1/1-1/2+1/3-1/4+1/5 …… + 1/99 - 1/100 的值 。
        int flag = 1;
        double res = 0.0;
        for (int i = 1; i < 101; i++) {
            res = res + ((1 / (double) i) * flag); //先对i进行转换
            flag = -flag;
        }
        System.out.println(res);
    }

    public static void main7(String[] args) {
        //输出100~999之间的水仙花数
        for (int i = 100; i < 1000; i++) {
            int a = i % 10; //个位
            int b = i / 10 % 10; //十位
            int c = i / 100 % 10; //百位
            if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == i) {
                System.out.println(i);
            }
        }
    }

    public static void main6(String[] args) {
        //猜数字游戏
        int num = (int) (Math.random() * 100 + 1);
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == num) {
                System.out.println("猜对了");
                break;
            } else if (n < num) {
                System.out.println("猜小了");
            } else {
                System.out.println("猜大了");
            }
        }
    }

    public static void main5(String[] args) {
        //根据输入的年龄, 来打印出当前年龄的人是少年(低于18), 青年(19-28), 中年(29-55), 老年(56以上)
        Scanner sc = new Scanner(System.in);
        BufferedReader scc = new BufferedReader(new InputStreamReader(System.in));

        int age = sc.nextInt();
        if (age < 0 || age > 150) {
            System.out.println("你能活这么久啊！！！");
        } else if (age <= 18) {
            System.out.println("少年");
        } else if (age <= 28) {
            System.out.println("青年");
        } else if (age <= 55) {
            System.out.println("中年");
        } else {
            System.out.println("老年");
        }
    }

    public static void main4(String[] args) {
        //判断一个数，是不是素数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i = 2;
        for (i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                break;
            }
        }
        if (i > Math.sqrt(n)) {
            System.out.println(n + "是素数");
        } else {
            System.out.println(n + "不是素数");
        }
    }

    public static void main3(String[] args) {
        //打印 1 - 100 之间所有的素数. 能被1和他本身整除的数
        for (int i = 2; i < 101; i++) {
            int j = 0;
            for (j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > Math.sqrt(i)) {
                System.out.println(i + " ");
            }
        }
    }

    public static void main2(String[] args) {
        //输出1000 ~2000之间的闰年
        //1、能被4整除且不能被100整除。  2、能被400整除
        for (int i = 1000; i < 2001; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                System.out.println(i + " ");
            }
        }
    }

    public static void main1(String[] args) {

        int count = 0;
        for (int i = 1; i <= 100; i++) {
            char[] s = String.valueOf(i).toCharArray();
            for (int j = 0; j < s.length; j++) {
                if (s[j] == '9') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
