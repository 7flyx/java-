package demo9;

public abstract class Athlete extends Person{

    //构造方法
    public Athlete() {

    }
    public Athlete(String name, int age) {
        super(name, age);
    }

    //方法重写
    @Override
    public void eat() {
        System.out.println("吃营养餐");
    }

    //particular 特有的方法
    public abstract void drill();
}
