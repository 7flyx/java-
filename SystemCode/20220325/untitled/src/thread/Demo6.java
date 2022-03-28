package thread;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-28
 * Time: 15:52
 * Description: synchronized 使用
 */
public class Demo6 {
    private static Object locker1 = new Object();
    private static Object locker2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) { // 当输入一个数后，会解锁，然后再次尝试加锁，此时的加锁就会和t2线程争抢，谁能抢到还得看底层的实现原理
               synchronized (locker1) { // 对locker1对象进行加锁
                   System.out.println("请输入一个整数；");
                   int a = sc.nextInt(); // 等待输入
                   System.out.println("a  " + a);
               }
           }
        });
        t1.start();

        // 添加睡眠，保存t1线程在t2线程之前启动
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
//           synchronized (locker1) { // 再次针对locker1加锁，因为此时它已被t1线程加锁了，只能阻塞等待
//               System.out.println("hello t2");
//           }
           synchronized (locker2) { // 对locker2加锁，因为locker2此时没有被加锁，所以当前线程不会进入阻塞等待
               System.out.println("hello t2");
           }
        });
        t2.start();
    }
}
