package demo2;

public class demo1 {
    public static void main(String[] args) {
        //测试继承中，使用变量，访问变量的先后顺序
        //在子类中访问一个变量，首先访问的是方法中的局部变量
        //第二访问就是这个子类中的成员变量
        //第三就是去访问父类中的成员变量，如果还没有，就会报错
        //this 访问本类中的成员变量，super 访问父类中的成员变量
        Zi z = new Zi();

        z.show();
    }
}
