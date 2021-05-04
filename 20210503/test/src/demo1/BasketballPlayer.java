package demo1;

public class BasketballPlayer extends Player{

    public BasketballPlayer() {
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("篮球运动员吃大餐");
    }
    public void study() {
        System.out.println("篮球运动员训练传球与投篮");
    }
}
