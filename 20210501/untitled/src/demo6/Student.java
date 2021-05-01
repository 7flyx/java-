package demo6;

public class Student extends Person{

    //构造方法
    public Student() {
    }
    public Student(String name, int age) {
        super(name,age);
    }

    public void Study() {
        System.out.println("好好学习");
    }
}
