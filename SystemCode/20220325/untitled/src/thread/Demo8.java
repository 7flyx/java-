package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-29
 * Time: 10:31
 * Description:
 */
public class Demo8 {
    static Object object = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (object) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("hello t1");
                    try {
                        object.wait(); // wait方法，会将进入等待队列，然后会将锁释放，等待被唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            synchronized (object) {
                System.out.println("hello t2");
            }
        });
        t2.start();
    }
}
