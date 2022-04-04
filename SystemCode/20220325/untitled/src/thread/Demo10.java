package thread;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-29
 * Time: 21:08
 * Description: 阻塞队列-  生产者消费者模型
 */

class MyBlockingQueue {
    private int[] items = new int[1000];
    private int head;
    private int tail;
    private Object locker = new Object(); // 锁对象

    public void put(int value) throws InterruptedException {
        synchronized (locker) {
            if ((tail + 1) % items.length == head) {
                // return; // 队列满了的情况
                locker.wait(); // 当前队列已经满了，需要等待队列弹出元素后才能加入
            }
            items[tail++] = value;
            tail = tail % items.length;
            locker.notify(); // 唤醒take方法中的 wait方法
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (locker) { // 加锁，保证线程安全
            if (head == tail) {
//                return null; // 空队列的情况
                locker.wait(); // 当前队列为空，需要等待新的元素添加进去，然后唤醒
            }
            int ret = items[head++];
            head = head % items.length;
            locker.notify(); // 唤醒在put方法中，启动的wait
            return ret;
        }
    }
}

class Producer implements Runnable {
    private MyBlockingQueue queue;

    public Producer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int n = 1;
        while (true) {
            System.out.println("producer: " + n);
            try {
                queue.put(n); // 添加新产品
                n++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 生产速度慢，消费速度快
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class Consumer implements Runnable {
    private MyBlockingQueue queue;

    public Consumer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int n = queue.take();
                System.out.println("consumer: " + n);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 消费速度慢，生产速度快的情况
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Demo10 {
    private static MyBlockingQueue queue = new MyBlockingQueue();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Producer(queue));
        Thread t2 = new Thread(new Consumer(queue));
        t1.start();
        t2.start();

    }

    public static void main1(String[] args) throws InterruptedException {
        queue = new MyBlockingQueue();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);

        for (int i = 0; i < 4; i++) {
            System.out.println(queue.take());
        }
    }
}
