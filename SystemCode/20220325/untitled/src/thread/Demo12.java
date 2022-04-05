package thread;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-05
 * Time: 15:02
 * Description:
 */
public class Demo12 {
    private static volatile int flag = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag == 0) {
                System.out.println("hello world");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入参数：");
        flag = sc.nextInt();
    }
}
