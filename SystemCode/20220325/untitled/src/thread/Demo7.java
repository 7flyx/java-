package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-28
 * Time: 19:17
 * Description: wait和notify
 *          在java中，对某一个对象实现wait或者notify，必须是在加锁的情况下调用
 */
class Node {
    int res = 0;
}

public class Demo7 {
    private static Node node = new Node();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           while (true) {
               synchronized(node) {
                   node.res++;
                   System.out.println("t1: " + node.res);
                   System.out.println("wait1 开始");
                   try {
                       node.wait(); // 调用wait方法
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("wait1 结束");
               }
           }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized(node) {
                    node.res++;
                    System.out.println("t2: " + node.res);
                    System.out.println("wait2 开始");
                    try {
                        node.wait(); // 调用wait方法
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait2 结束");
                }
            }
        });
        t2.start();

        try {
            Thread.sleep(3000); // 添加间隔时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (node) {
            System.out.println("notify 开始");
            node.notifyAll(); // 唤醒object对象的其中一个线程
            System.out.println("notify 结束");
        }
    }
}
