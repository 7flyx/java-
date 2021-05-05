package demo4;

public class IntegerDemo {
    public static void main(String[] args) {
        //Integer类，String类。int类型如何与String类型相互转换

        //int  -》  string
        //方式1
        int a = 100;
        String s1 = "" + a; //直接用拼接
        System.out.println(s1);

        //方式2，用String类方法
        String s2 = String.valueOf(a); //static修饰，不需要new一个对象出来
        System.out.println(s2);
        System.out.println("------------");


        //String  -》 int
        //方式1
        String s3 = "100";
        int a2 = Integer.parseInt(s3);  //转换为有符号十进制整数，还有一个无符号数的方法可以调用
        System.out.println(a2);

        //方式2---  String -> Integer  -> int
        Integer i1 = Integer.parseInt(s3);
        int x = i1.intValue();
        System.out.println(x);

    }
}
