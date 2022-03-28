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

    // 没加锁的情况下，会发生 加载到CPU寄存器时，产生错误，导致计算的结果有误
//    public void increase() {
//        count++;
//    }

    // 加锁的情况。就不会出现在加载数据到寄存器时，产生“同时加载”的情况
    // 虽然此处加锁后，会影响到并发性，但也只会影响到当前方法的并发性，当前类的其他方法并不会影响
    // 所以会影响到一定的并发性，但是整体上来看，多线程技术还是有很大的优势
//    synchronized public void increase() {
//        count++;
//    }

    public void increase() {
        synchronized (this) {
            count++;
        }
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
