package demo4;

public class Cat extends Animal{

    //构造方法
    public Cat() {
    }
    public Cat(String name, int age) {
        super(name, age);
    }

    //重写方法

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}
