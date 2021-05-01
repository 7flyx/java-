package demo6;

public class Teacher extends Person{

    //构造方法
    public Teacher(){
    }
    public Teacher(String name, int age) {
        super(name,age);
    }

    //自己属性的类方法
    public void Teach() {
        System.out.println("教书育人");
    }
}
