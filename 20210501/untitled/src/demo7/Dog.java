package demo7;

public class Dog extends Animal{

    //构造方法
    public Dog() {
    }
    public Dog(String name, int age) {
        super(name,age);
    }

    //成员方法
    public void work() {
        System.out.println("好好看门");
    }
}
