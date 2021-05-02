package demo7;

public class Cat implements Jumping{

    //jumping中有抽象方法，此处需要进行重写
    @Override
    public void jump() {
        System.out.println("猫可以跳高了");
    }

    @Override
    public void jump2() {
        System.out.println("猫还可以跳");
    }
}
