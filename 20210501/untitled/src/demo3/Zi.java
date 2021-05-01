package demo3;

public class Zi extends Fu{
    public Zi() {
        System.out.println("Zi类中无参构造方法");
    }
    public Zi(int age) {
        super(20);
        System.out.println("Zi类中带参构造方法");
    }
}
