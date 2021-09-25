import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-24
 * Time: 15:34
 * Description: 测试异常类
 */
public class Demo {

    public static void main(String[] args) {


        try {
            //假设此处会产生一个异常
        } catch (IOException e) {

        } catch (RuntimeException e) {

        } catch (Exception e) {

        }


        try {
            //假设此处会产生一个异常
        } catch (Exception e) {

        } catch (RuntimeException e) {

        } catch (IOException e) {

        }

    }

    public static void main4(String[] args) {
        String s1 = "welcome to bit";
        String s2 = "come";
        func(s1, s2);
    }

    public static void func(String str1,String str2) {
        if (str1 == null || str2 == null || str1.length() < 1) {
            return;
        }
        if (str2.length() < 1) {
            System.out.println(str1);
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < s1.length; i++) {
            if (!isContiansCh(s1[i], s2)) {
                list.add(s1[i]);
            }
        }

        for (Character ch : list) {
            System.out.print(ch);
        }
    }

    private static boolean isContiansCh(char ch, char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ch) {
                return true;
            }
        }
        return false;
    }

    public static void main3(String[] args) {
        System.out.println(func());
    }

    public static int func() {
        try{
            return 10;
        } finally {
            return 20;
        }
    }

    public static void main2(String[] args) {
        //假设现在我们需要输入两个参数：
        //1-》 开始游戏
        //0-》退出游戏
        //结果用户在输入的时候，输入既不是1也不是0。那么此时我们就可以自己手动地产生一个异常
        Scanner sc = new Scanner(System.in);
        System.out.println("1-> 开始游戏     0->退出游戏");
        int n = sc.nextInt();

        if (n != 1 && n != 0) {
            throw new RuntimeException("输入参数非法");
        }
    }

    public static void main1(String[] args) {
        int[] arr = new int[10]; //长度为10的数组
        try {
            System.out.println("越界访问之前");
            System.out.println(arr[11]); //越界访问
            System.out.println("这里的代码不会被执行");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println("越界访问之后");
    }
}
