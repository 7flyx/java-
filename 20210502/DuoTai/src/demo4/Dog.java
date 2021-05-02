package demo4;

import demo2.AnimalOprator;

public class Dog extends Animal {

    //构造方法
    public Dog() {

    }
    public Dog(String name, int age) {
        super(name, age); //访问父类中的变量
    }

    //重写eat

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}
