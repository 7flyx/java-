package demo8;

import demo7.Jumping;

public class Dog extends Animal implements Jumpping {

    //构造方法
    public Dog() {

    }
    public Dog(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void jump() {
        System.out.println("狗可以跳高了");
    }
}
