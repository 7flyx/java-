package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-02
 * Time: 16:40
 * Description: 实现简单的线程池
 */
class MyThreadPool {
    // 用阻塞队列存储所需要执行的任务
    private BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
    private List<Worker> list = new ArrayList<>(); // 存储当前线程池中所有的线程

    // 构造方法，参数是线程数
    public MyThreadPool(int n) {
        for (int i = 0; i < n; i++) {
            Worker worker = new Worker(queue);
            worker.start(); // 先让线程跑起来，然后再存储到数组中
            list.add(worker);
        }
    }

    // 一个线程
    static class Worker extends Thread {
        // 存储任务的阻塞队列
        private BlockingQueue<Runnable> queue;

        public Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 如果队列中没有任务，那就会进行等待
                    Runnable runnable = queue.take();
                    runnable.run(); //执行任务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 向外提供添加任务接口
    public void submit(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class RealizeMyThreadPool {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(10); // 线程池里面创建10个线程
        for (int i = 0; i < 100; i++) {
            pool.submit(new Runnable() { // 往里面塞任务
                @Override
                public void run() {
                    System.out.println("hello thread");
                }
            });
        }
    }
}
