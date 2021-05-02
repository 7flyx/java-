package demo2;

//测试类
public class AnimalDemo {
    public static void main(String[] args) {
        //多态的好处与弊端
        //好处：提高了程序的扩展性，弊端：不能使用类里面特有的方法

        Cat c = new Cat();
        Dog d = new Dog();
        AnimalOprator an = new AnimalOprator();

        an.useAnimal(c); //传递的是子类，成员方法那边接收的父类
        an.useAnimal(d);

    }
}
