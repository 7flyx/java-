package demo8;

public class Student {

    public String name;
    public int age;
    public static String school;  //静态修饰方法变量

    public void show() {
        System.out.println(name + "," + age + "," + school);
    }

    public static void show2() {
        System.out.println("静态成员方法");
    }
}
