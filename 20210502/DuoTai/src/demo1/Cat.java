package demo1;

public class Cat extends Animal{
    public int age = 20;
    public int weight = 90;

    @Override
    public void eat() {
        System.out.println("猫吃鱼");  //方法重写
    }

    public void catc() {
        System.out.println("抓老鼠");
    }
}
