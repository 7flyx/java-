package demo2;

import demo1.Student;

import java.util.Comparator;
import java.util.TreeSet;

public class demo2 {
    public static void main(String[] args) {
        //TreeSet,存储学生类信息，TreeSet会根据构造方法进行一定的排序，继承了Set接口，所有还是不会出现重复的数据
        TreeSet<Student> tr = new TreeSet<Student>(new Comparator<Student>() {  //匿名内部类的构造
            @Override
            public int compare(Student o1, Student o2) {
                int num = o1.getAge() - o2.getAge();
                int num2 = num == 0 ? o1.getName().compareTo(o2.getName()) : num;
                return num2;
            }
        });  //这里的构造方法，可以实现具体的排序方式,--类型中需要实现一定的接口

        Student s1 = new Student("张三", 20);
        Student s2 = new Student("李四", 10);
        Student s3 = new Student("王麻子", 66);
        tr.add(s1);
        tr.add(s2);
        tr.add(s3);

        for(Student s : tr) {
            System.out.println(s.getName() +", " + s.getAge());
        }
    }
}
