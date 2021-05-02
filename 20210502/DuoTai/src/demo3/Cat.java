package demo3;

import sun.awt.HeadlessToolkit;

public class Cat extends Animal{

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }

    public void play() { //类中特有的方法
        System.out.println("猫抓老鼠");
    }
}
