package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-26
 * Time: 19:45
 * Description: 线程不安全
 */

class Counter {
    public int count;

    public void increase() {
        count++;
    }
}

public class Demo5 {
    private static Counter counter = new Counter();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    counter.increase();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    counter.increase();
                }
            }
        };

        t1.start();
        t2.start();

        // 等待线程--此时t1和t2已经开始跑起来了，这里只是为了让都跑结束，main线程才停止
        t1.join();
        t2.join();


        // 理应来说，此时的count应该是10w，但是是两个线程不安全的 跑的
        // 所有存在差距
        // 定义：在多线程编程中，如果出现了bug，就称为是 线程不安全的
        // 反之，就是线程安全的
        System.out.println(counter.count);
    }
}
