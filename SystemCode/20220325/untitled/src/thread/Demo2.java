package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-25
 * Time: 21:02
 * Description:
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello runnable");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Demo2 {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable()); // 往线程里面塞一个runnable
        t.start(); // 真正的系统层面开启线程

        while (true) {
            System.out.println("hello main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
