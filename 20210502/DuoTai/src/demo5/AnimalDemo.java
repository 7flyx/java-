package demo5;

public class AnimalDemo {
    public static void main(String[] args) {
        //抽象类
        //Animal a = new Animal(); //此时的Animal类是抽象类，不能进行实例化，需要用到多态的思想

        Animal a = new Cat();  //通过子类，多态的思想去实例化 抽象类Animal
        a.eat();
    }
}
