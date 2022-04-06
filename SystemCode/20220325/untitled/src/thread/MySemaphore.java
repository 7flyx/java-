package thread;

import java.util.concurrent.Semaphore;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-06
 * Time: 10:43
 * Description: semaphore 信号量，用来表示“可用资源的个数”，就是一个 计数器，用于计数的
 */
public class MySemaphore {
    public static void main(String[] args) {
        // 实参是一个整数，表示计数的上限值。里面的计数器计算，都是原子性的，可以直接在多线程下进行使用
        Semaphore semaphore = new Semaphore(3);
        try {
            semaphore.release(); // 回收计数，也就是计数器--。称为 P操作
            semaphore.acquire(); // acquire，就是获取计数，表示计数器++。 称为V操作
            System.out.println("计数成功");
            semaphore.acquire();
            System.out.println("计数成功");
            semaphore.acquire();
            System.out.println("计数成功");
            semaphore.acquire(); // 当计数器已经达到上限时，此处的acquire就会进行阻塞等待，等有人将前面的计数回收了
            System.out.println("计数成功");
            semaphore.release(); // 回收计数，也就是计数器--。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
