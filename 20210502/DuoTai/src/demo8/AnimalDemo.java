package demo8;

public class AnimalDemo {
    public static void main(String[] args) {
        //接口 抽象类 结合版的 猫和狗

        Animal a = new Cat("TOM",20);
        System.out.println(a.getName() + ", " + a.getAge());
        //a.jump(); //具体的类型是Animal 类型，这个类型里面并没有jump方法，需要转换为Cat类才可以调用jump方法


        //要同时使用animal里面的方法，还有使用接口的方法，那么只能创建具体的cat类，因为继承了animal 也实现了接口
        Dog d = new Dog("gou",2);
        d.eat();
        System.out.println(d.getName()  + ", " + d.getAge());
        d.jump();
    }
}
