package demo7;

//接口类
public interface Jumping {

    //在接口类中，成员变量默认是  public static final 修饰的，即就是  常量  静态
    int age3 = 10;
    public int age = 20;
    public static final int age2 = 30;


    public abstract void jump();  //接口类本身就是抽象的，不需要关键字abstract

    void jump2();  //在结构类中，成员方法，默认的也是public abstract ，且必须是抽象的，不能使用具体的方法
}
