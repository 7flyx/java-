package thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-31
 * Time: 12:34
 * Description: 定时器 Timer
 */
public class Demo11 {
    public static void main(String[] args) {
        Timer timer = new Timer(); // 定时器--底层实现原理就是一个小根堆，在搞一个线程扫码堆顶数据是否到点了
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello timer");
            }
        }, 3000); // 传入的是TimerTask任务，延时时间是 毫秒

        System.out.println("hello main");
    }
}
