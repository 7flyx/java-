package thread;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-31
 * Time: 14:20
 * Description: 模拟实现定时器
 */

class MyTimer {
    // 底层是一个堆
    private PriorityBlockingQueue<Task> queue;
    private Object locker = new Object(); // 使用线程等待的锁对象
    public MyTimer() {
        queue = new PriorityBlockingQueue<Task>();
        Thread thread = new Thread() { // 单独创建一个线程，用于扫描小根堆的情况
            @Override
            public void run() { // 重写run方法
                while (true) { // 死循环，一直检查小根堆的堆顶袁术是不是已经到点了
                    try {
                        Task task = queue.take(); // 不会拿到null值，因为这是“阻塞堆”，在没有数据的时候，会进行等待
                        long curTime = System.currentTimeMillis();
                        if (curTime < task.time) { // 还没到点的情况，使用线程等待
                            queue.put(task);  // 还没到点的时候，再次将数据压入堆中
                            synchronized (locker) { // 使用线程等待的前提是，先加锁
                                locker.wait(task.time - curTime); // 等待时间是二者的差值，单位是毫秒
                            }
                        } else { // 已经到点了，该执行任务了
                            task.run();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start(); // 将线程跑起来
    }

    // 设置任务
    public void schedule(Runnable runnable, long delay) {
        Task task = new Task(runnable, delay);
        queue.put(task); // 将任务放入小根堆中
    }

    // 任务
    public static class Task implements Comparable<Task>{
        private Runnable runnable; // 任务名
        private long time; // 多少毫秒后开始执行

        public Task(Runnable runnable , long delay) {
            this.runnable = runnable;
            this.time = delay + System.currentTimeMillis(); // 当前时间加上 延时时间
        }

        public void run() {
            runnable.run();
        }

        public int compareTo(Task o) {
            return (int)(this.time - o.time);
        }
    }
}



public class Demo12 {
    public static void main(String[] args) {
        MyTimer timer = new MyTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello my timer");
            }
        }, 3000);

        System.out.println("hello main");
    }
}
