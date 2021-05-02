package demo6;

public class AnimalDemo {
    public static void main(String[] args) {
        //抽象类版本的   猫和狗
        Animal a = new Cat("TOM",2); //多态访问 抽象类
        a.eat();

        a = new Dog("gou",20);
        a.eat();
        System.out.println(a.getName() + "," + a.getAge());
    }
}
