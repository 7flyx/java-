package demo2;

public class Cat extends Animal{

    @Override
    public void eat() {  //方法重写
        System.out.println("猫吃鱼");
    }

    public void paly() {  //这个类特有的成员方法，并非重写，当通过多态的角度去访问时，会报错
        System.out.println("玩耍");
    }
}
