package demo2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class demo1 {
    public static void main(String[] args) {
        //collection 集合，创建学生类，并进行遍历操作

        Collection<Student> c = new ArrayList<>();
        Student s1 = new Student("飞人",33);
        Student s2 = new Student("苏炳添",32);
        Student s3 = new Student("谢霆锋",32);

        c.add(s1);
        c.add(s2);
        c.add(s3);

        //创建迭代器进行遍历操作
        Iterator<Student> it = c.iterator();
        while(it.hasNext()) {
            Student s = it.next();
            System.out.println(s.getName() + ", " + s.getAge());
        }
    }
}
