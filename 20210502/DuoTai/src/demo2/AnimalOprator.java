package demo2;

public class AnimalOprator {

    //单独有一个类，用于参数Animal的接收，让父类指向子类
    public void useAnimal(Animal a) {  //例如 ： Animal a = new Cat();
        a.eat(); //访问成员方法时： 编译看父类，执行看子类
    }
}
