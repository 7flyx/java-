package demo1;

public class demo1 {
    public static void main(String[] args) {
        //泛型的使用，泛型类 泛型方法
        //public class Student<T> {},    public <T> void show(T t) {}

        Tearch<String> t1 = new Tearch<String>("飞人","30");
        t1.show();
        t1.show("you never give up");


    }
}
