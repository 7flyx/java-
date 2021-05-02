package demo4;

public class AnimalDemo {
    public static void main(String[] args) {
        //多态的方法去访问动物类 猫类 与 狗类
        Animal a = new Cat();
        a.setName("TOM");
        a.setAge(20);  //动物类自身的方法
        a.eat();  //重写的方法
        System.out.println(a.getName() + "," + a.getAge());

        Animal a2 = new Dog("dog",20);
        a2.eat();
        System.out.println(a2.getName() + "," + a2.getAge());
    }
}
