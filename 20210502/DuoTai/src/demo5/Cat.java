package demo5;

public class Cat extends Animal{
    //继承了抽象类，必须重写父类中所有的抽象方法


    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    public void play2() { //也可以定义自己特有的方法
        System.out.println("猫抓老鼠");
    }
}
