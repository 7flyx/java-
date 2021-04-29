package demo1;

public class stringBuilderDemo {
    public static void main(String[] args) {
        //StringBuilder类的使用
        //与String 类的区别
        //1. string类里面的内容是不可变的---- StringBuilder类里面的内容是可变的
//        String s = "hello";  //存储在常量池
//        String s1 = s + "world";  //首先会在常量池生成“world”，还得再生成“hello world”，把这个内存地址赋值给s1
//        System.out.println(s1);



//        StringBuilder s2 = new StringBuilder(); //无参构造方法
//        System.out.println("s2:" + s2);
//        StringBuilder s3 = new StringBuilder("hello world");
//        System.out.println("s3:" + s3);



        //public StringBuilder append(),添加数据，返回的是对象本身
        StringBuilder sb = new StringBuilder();

        StringBuilder sb2 = sb.append("hello");
        System.out.println("sb2:" + sb2);//sb2:hello
        System.out.println("sb:" + sb); // sb:hello
        System.out.println(sb == sb2);//true  说明sb.append(),返回的还是sb本身这个对象

        sb.append("world");  //并没有用新的对象来接收返回值
        sb.append("java");
        sb.append(100);
        System.out.println("sb:" + sb); //sb:helloworldjava100

        //链式编程
        sb.append("---").append("2021").append("04").append(28);  //可以传任意类型的数据类型，都会变成字符串类型，且不会在
                                                                //内存中产生多余的内存空间
        System.out.println("new sb:" + sb); //helloworldjava100---20210428

        //字符串反转
        sb.reverse();
        System.out.println("reverse:" + sb); //82401202---001avajdlrowolleh
    }
}
