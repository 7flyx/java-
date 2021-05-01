package demo4;

public class Zi extends Fu{
    public void menthod() {
        System.out.println("Zi类中method方法调用");
    }

    @Override   //override 注解，用于显示方法的重写，命名是否正确
    public void show() {
        System.out.println("Zi类中show方法调用");
    }
}
