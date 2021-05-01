package demo2;

public class Zi extends Fu {
    public int height = 175;
    public int age = 20;

    public void show() {
        int age = 10;
        System.out.println("age:" + age); //访问的是这个循环里面的局部变量
        System.out.println("height:" + height);
        System.out.println("age:" + this.age); //加了this，访问的是本类中的成员变量
        System.out.println("age:" + super.age); //super，代表父类存储空间的标识
    }
}
