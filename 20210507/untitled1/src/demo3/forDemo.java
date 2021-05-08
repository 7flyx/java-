package demo3;

import demo2.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class forDemo {
    public static void main(String[] args) {
        //增强for循环的使用（底层还是一个迭代器），以及List集合的练习
        //增强for规范     for(数据类型  变量名 ： collection集合 或者 数组)

        List<Student> c = new ArrayList<>();
        Student s1 = new Student("飞人", 30);
        Student s2 = new Student("江上", 20);
        Student s3 = new Student("南方", 20);

        c.add(s1);
        c.add(s2);
        c.add(s3);

        //三种遍历方式--普通for 增强for 迭代器
        for (int i = 0; i < c.size(); i++) {
            Student s = c.get(i);  //Collection接口，不能通过索引进行元素的读取
            System.out.println(s.getName() + ", " + s.getAge());
        }

        for (Student s : c) {
            System.out.println(s.getName() + ", " + s.getAge());
        }

        ListIterator<Student> it = c.listIterator();
        while (it.hasNext()) {  //向后访问
            Student s = it.next();
            System.out.println(s.getName() + ", " + s.getAge());
        }

        while (it.hasPrevious()) { //向前访问
            Student s = it.previous();
            System.out.println(s.getName() + ", " + s.getAge());
        }


    }
}
