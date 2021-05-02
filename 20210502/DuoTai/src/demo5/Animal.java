package demo5;

public abstract class Animal {

    //成员方法
//    public void eat() {  //具体的方法，有具体的方法体
//        System.out.println("吃东西");
//    }
//
    public abstract void eat(); //没有具体的方法体，称为抽象方法，关键字 abstract，有抽象方法，则该类必须是抽象类

    public void play() {
        System.out.println("玩耍"); //抽象类中可以有具体的方法
    }
}
