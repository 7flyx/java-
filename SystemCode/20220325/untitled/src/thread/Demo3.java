package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-25
 * Time: 21:07
 * Description:
 */
public class Demo3 {
    public static void main(String[] args) {
        Thread t = new Thread() { // 匿名内部类的形式
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
        };

        Thread t2 = new Thread(new Runnable() { // 还是匿名内部类 的情况
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
        });

    }
}
