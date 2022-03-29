package thread;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-29
 * Time: 20:38
 * Description: 单例模式- 饿汉模式和懒汉模式
 */
class SingleModel {
    // 直接在字段这里进行创建对象，称为 饿汉模式
    // 因为这个字段是在类加载后，不久就会被创建
    // 因为这个对象的初始化，是在类加载的时候被创建的，在多线程的情况下，不会因为线程调度等问题导致线程不安全
    private static SingleModel instance = new SingleModel();

    // 将构造方法设为private
    private SingleModel() {}

    public static SingleModel getInstance() {
        return instance; // 直接返回实例对象
    }
}

class SingleModel2 {
    // 字段初始化为空，在需要获取这个实例对象的时候，进行实例化对象
    // 这种就称为懒汉模式，是在需要的时候，才会实例化对象
    private static SingleModel2 instance = null;

    // 构造方法还是设置为private，不对外界提供构造方法
    private SingleModel2() {}

    // 但是这种懒行模式是线程不安全的，因为在判断instance==null时，可能会产生多个线程同时进行判断
    // 导致里面的字段被赋值多次
    public static SingleModel2 getInstance() {
        if (instance == null) { // 当前还没初始化，就给这个参数实例化一个对象
            instance = new SingleModel2();
        }
        return instance;
    }
}

class SingleModel3 {
    // 还是字段初始化为null----懒汉模式
    private static SingleModel3 instance = null;

    private SingleModel3() {}

    public static SingleModel3 getInstance() {
        if (instance == null) { // 首先判断是否初始化了，不然的话，以下代码每次调用就会加一次锁，浪费时间
            synchronized (SingleModel3.class) { // 进行加锁，传入参数是类对象
                if (instance == null) { // 在还没初始化的时候，实例化一个对象
                    instance = new SingleModel3();
                }
            }
        }
        return instance;
    }
}

public class Demo9 {
    public static void main(String[] args) {

    }
}
