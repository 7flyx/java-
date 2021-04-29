package demo2;

public class Student {
    private String name;
    private int age;  //private 修饰，仅在这个类的里面进行操作，即就是需要写相应的类方法

    public Student() {

    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
