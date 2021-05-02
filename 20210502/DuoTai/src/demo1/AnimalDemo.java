package demo1;

public class AnimalDemo {
    public static void main(String[] args) {
        //多态---
        //前提是1. 要有继承或者实现关系
        //2. 要有方法重写
        //3. 要有父类指向子类

        Animal a = new Cat();  //父类指向子类
        // 访问成员变量 ： 1. 编译看父类 ， 执行看父类
        System.out.println(a.age); //访问的是父类中age
       // System.out.println(a.weight); //此时父类并没有weight变量，报错

        //访问成员方法: 2. 编译看父类， 执行看子类
        a.eat(); //猫吃鱼,重写之后的eat方法
       // a.cach(); //编译时，父类并没有catc这个方法，报错

    }
}
