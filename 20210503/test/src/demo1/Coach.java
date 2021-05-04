package demo1;

public abstract class Coach extends Person{

    public Coach() {
    }

    public Coach(String name, int age) {
        super(name, age);
    }

    //重写方法
    public abstract void eat();
    public abstract void teach();
}
