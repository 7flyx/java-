package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-26
 * Time: 16:45
 * Description: 线程中的一些基本使用
 *
 *           interrupt（），表示发起中断请求，会将标志位设为true
 *           static boolean interrupted（），判断标志位是不是true，然后清除标志位，也就是改为false
 *           isInterrupted()，判断标志位是不是true，不会清除标志位，也就是不会更改
 *
 */
public class Demo4 {
    public static void main(String[] args) {
        Thread t = new Thread() { // 直接匿名创建Thread对象
            @Override
            public void run() {
                while (!this.isInterrupted()) { // 看线程是否中断
                    System.out.println("hello thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 因为在线程被调用interrupt时，这个方法这是将一个标志性变量改为true
                        // 之后，判断当前线程的状态，是否处于 阻塞状态
                        // 如果是阻塞状态，则会触发当前的异常，并将标志性变量改为false
                        break;
                    }
                }
            }
        };

        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt(); // 中断线程
    }
}
