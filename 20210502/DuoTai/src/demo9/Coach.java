package demo9;

public abstract class Coach extends Person{

    //构造方法
    public Coach() {
    }
    public Coach(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("吃工作餐");
    }

    //particular 特有的方法
    public abstract void Teach();
}
