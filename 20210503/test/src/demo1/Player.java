package demo1;

public abstract class Player extends Person{

    public Player() {
    }

    public Player(String name, int age) {
        super(name, age);
    }

    //重写方法
    public abstract void eat();
    public abstract void study();
}
