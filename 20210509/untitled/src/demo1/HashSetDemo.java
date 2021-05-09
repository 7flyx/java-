package demo1;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        //学生类，写出HashSet集合
        HashSet<Student> hs = new HashSet<>();

        Student s1 = new Student("飞人",30);
        Student s2 = new Student("博尔特",33);
        Student s3 = new Student("林俊杰",33);
        Student s4 = new Student("飞人",30);

        hs.add(s1);
        hs.add(s2);
        hs.add(s3);
        hs.add(s4);  //如果不重写Student类里面的Hashcode和equals方法，set还是会将重复的元素添加进去

        for (Student s : hs) {
            System.out.println(s.getName() + ", " + s.getAge());
        }
    }
}
