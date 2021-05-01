package demo7;

public class Cat extends Animal{

    //构造方法
    public Cat() {
    }
    public Cat(String name, int age) {
        super(name, age);
    }

    //成员方法
    public void work() {
        System.out.println("好好抓老鼠");
    }
}

