package demo2;

//测试类
public class demo {
    public static void main(String[] args) {
        //接口类作为实参传递和作为类型接收的两种情况
        AnimalOprator ao = new AnimalOprator();
        Animal a = new Cat();  //多态思想，父类指向子类
        ao.useAnimal(a);
        System.out.println("--------------");

        Jumpping j = new Dog();
        ao.useAnimal(j);

        System.out.println("--------------");
        Jumpping j2 = ao.ret();
        j2.jump();

        Animal a2 = ao.ret1();
        a2.eat();
    }
}
