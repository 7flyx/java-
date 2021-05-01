package demo7;

public class demo1 {
    public static void main(String[] args) {
        //一样的，用继承的思想实现  猫狗类的创建

        Dog d = new Dog(); //无参构造
        d.setName("汪汪");
        d.setAge(20);
        System.out.println(d.getName() + "," + d.getAge());
        d.work();

        Cat c = new Cat("汤姆",20); //带参构造
        System.out.println(c.getName() + "," + c.getAge());
        c.work();
    }
}
