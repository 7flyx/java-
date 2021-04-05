package test2;

public class Private {
    //方法变量
    String name;
    private int age;  //private 关键字修饰，只能在这个类中使用，可以定义类中的方法，进行赋值运算

    public int getAge() {
        return age;
    }
    public void setAge(int a) {
        if(a > 0 && a < 120) {
            age = a;
        } else {
            System.out.println("你输入的年龄有误");
        }
    }

    public void call() {
        System.out.println("打电话");
    }
    public void getMass() {
        System.out.println("发短信");
    }
}
