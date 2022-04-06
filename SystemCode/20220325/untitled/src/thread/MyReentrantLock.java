package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-06
 * Time: 9:38
 * Description: callable 接口，和runnable类似，只是runnable接口的run方法是没有返回值。
 *              而callable接口的call方法，是有返回值的。且这个接口是搞了个泛型的
 */
public class MyReentrantLock {
    private static int res = 0;

    // 实现1~1000的累加和，基于Runnable接口上实现的
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           for (int i = 1; i <= 1000; i++) {
               res += i;
           }
        });
        t1.start();

        try {
            // 等待t1线程执行完
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(res);
    }

    // 实现1~1000的累加和，基于Callable接口实现的
    public static void main1(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 计算1~1000的累加和，并返回
                int res = 0;
                for (int i = 1; i <= 1000; i++) {
                    res += i;
                }
                return res;
            }
        };

        // 因为Callable接口不能直接进行创建线程，需要一个中介FutureTask类
        // 这个类就能获取到call方法的返回值
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t = new Thread(task);
        t.start();

        try {
            // 拿取task里面的Callable接口实现的call方法返回值，如果当前还没返回，就会进行等待
            Integer res = task.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
