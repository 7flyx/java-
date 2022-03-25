package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-25
 * Time: 20:48
 * Description: 初始多线程
 */

class MyThread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Demo1 {

    public static void main(String[] args) {
        MyThread1 t = new MyThread1();
        t.start();

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
