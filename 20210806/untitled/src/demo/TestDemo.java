package demo;

import java.util.Random;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-06
 * Time: 17:25
 */
public class TestDemo {

    //初始化块(实例代码块)
    {
        //初始化一些实例数据成员
        salary = 200;
        System.out.println("实例代码块");
    }
    static {
        //用于静态的实例字段的初始化和需要提前准备的一些数据
        staffID = 7000;
        //this.salary = 200;  // error 静态代码块，不能初始化非静态的实例字段
        System.out.println("静态代码块");
    }

    public int salary = 3000;
    public static int staffID = 100;

    public TestDemo() {
        System.out.println("无参构造方法");
    }
    public int reDouble(int num) { //翻倍
        salary = salary * num;
        return salary;
    }

    public static void main(String[] args) {
        TestDemo demo = new TestDemo();
        System.out.println(demo.salary);
        System.out.println(TestDemo.staffID);
    }

}
