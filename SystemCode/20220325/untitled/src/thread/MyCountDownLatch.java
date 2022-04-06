package thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-06
 * Time: 10:50
 * Description: CountDownLatch 同时等待N个任务 执行结束
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        // 实参部分就是总的任务数，全部完成后就会结束
        CountDownLatch count = new CountDownLatch(5);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.countDown(); // 完成任务数-1
                    System.out.println("当前任务完成了--" + i);
                }
            }
        };
        t1.start();

        // 阻塞等待设置的任务数完成。
        count.await();
    }
}
