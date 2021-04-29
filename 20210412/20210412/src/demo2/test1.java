package demo2;

public class test1 {
    public static void main(String[] args) {
        //类与对象

        Student s = new Student();
        s.setName("刘祥");
        s.setAge(19);
        s.show();

        Student s2 = new Student("刘祥");  //有参的构造方法，在创建new时，直接进行了调用方法
        s2.show();

    }
}
