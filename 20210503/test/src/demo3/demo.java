package demo3;


//测试类
public class demo {
    public static void main(String[] args) {

        //public内部类的访问格式
//    Outer.Inter oi = new Outer().new Inter();  //public修饰的内部类
//    oi.show();

        Outer o = new Outer();
        o.method();
    }

}
