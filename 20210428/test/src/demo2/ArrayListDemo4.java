package demo2;

import java.util.ArrayList;

public class ArrayListDemo4 {
    public static void main(String[] args) {
        //创建学生类，再通过学生类进行创建ArrayList集合的运用
        ArrayList<Student> array = new ArrayList<>();

        Student s1 = new Student("张三丰",30);
        Student s2 = new Student("苏炳添",32);
        Student s3 = new Student("博尔特",35);

        array.add(s1); //在添加这种有类的数据时，似乎不能直接添加，而是需要先建立对象，再添加到集合中
        array.add(s2);
        array.add(s3);

        Object[] ss =  array.toArray();
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            System.out.println(s.getName() + "," + s.getAge());
        }
    }
}

