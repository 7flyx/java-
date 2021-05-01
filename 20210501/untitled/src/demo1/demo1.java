package demo1;

public class demo1 {
    public static void main(String[] args) {
        //继承的基本思想
        //  public class Zi extends Fu() {}
        //子类中，可以直接使用父类里面的方法和变量

        Fu f = new Fu();
        f.show();

        Zi z = new Zi();
        z.method(); //自己的方法
        z.show(); //父类的方法
    }
}
