package demo8;

public class Cat extends Animal implements Jumpping{
                //先继承类，在实现接口
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
    @Override
    public void jump() {
        System.out.println("猫可以跳高了");
    }
}
