package demo1;

public class Tearch<T> implements impl<T>{
    private T name;
    private T age;

    public Tearch(T name, T age) {
        this.name = name;
        this.age = age;
    }

    public void show() {
        System.out.println(this.name + ", " + this.age);
    }

//    public <T> void show(T t1) {  //不需要在泛型类中在定义声明
//        System.out.println(t);
//    }

    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
