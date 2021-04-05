package test;

public class test1 {
    public static void main(String[] args) {
        //对象与类

        //创建类
//        Phone p = new Phone();  //类似于结构体变量的创建
//
//        System.out.println(p.brand); //字符串类型，没有进行初始化，默认是null
//        System.out.println(p.price); //int类型，没有进行初始化，默认是0
//
//        //成员变量的初始化
//        p.brand = "小米";
//        p.price = 2899;
//
//        System.out.println(p.brand);
//        System.out.println(p.price);


        //创建学生类的变量
        Student s = new Student();

        System.out.println(s.name + "," + s.age);  //为初始化，默认是null与0

        s.name = "飞人";
        s.age = 30;
        System.out.println(s.name + "," + s.age);

        //调用类里面的方法
        s.study();
        s.doHomework();

    }
}
