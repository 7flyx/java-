package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-01-16
 * Time: 20:03
 * Description:
 */
public class Demo2 {
    // 1、计算1~100，有多少个单数


    // 2、计算1~1000，哪些是单数，把它打印出来。
    public static void main(String[] args) {
        int huan = 1;
        for (int i = 1; i <= 1000; i++) {
            if (i % 2 == 1) {
                huan++;
                System.out.println(i);
            }
        }

    }
}