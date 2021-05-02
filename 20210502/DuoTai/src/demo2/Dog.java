package demo2;

public class Dog extends Animal{

    @Override
    public void eat() { //方法重写
        System.out.println("狗吃骨头");
    }
}
