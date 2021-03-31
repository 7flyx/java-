package test1;

public class test1 {
    public static void main(String[] args) {

        /*
        * 方法重载
        * 1. 必须在用一个类下
        * 2. 必须同名
        * 3. 参数必须不同，例如参数类型不同，数量不同
        *
        * */

        //方法调用--根据传递的参数，去调用相应的方法
        System.out.println(sum(10,20));
        System.out.println(sum(10.0,20.0));
        System.out.println(sum(10,20,30));

    }

    public static int sum(int a,int b) {
        return a+b;
    }

    public static double sum(double a,double b) {
        return a+b;
    }

    public static int sum(int a,int b,int c) {
        return a+b+c;
    }
}
