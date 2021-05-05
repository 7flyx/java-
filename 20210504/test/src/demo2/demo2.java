package demo2;

public class demo2 {
    public static void main(String[] args) {
        //object类中的equals

        Student s1 = new Student("张三",20);
        Student s2 = new Student("张三",20);

        System.out.println(s1.equals(s2)); //因为本身就是创建的对象，所以直接拿着比较，比较的是地址值，注意区分这里是类的equals
                                            //不是String.equals().怎么比较类里面的内r容，就需要在类中重写equals方法


    }
}
