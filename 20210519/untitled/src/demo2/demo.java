package demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class demo {
    public static void main(String[] args) {
        //用collections 对集合进行排序
        ArrayList<Student> array = new ArrayList<>();

        Student s1 = new Student("zhangsan",20);
        Student s2 = new Student("lisi",30);
        Student s3 = new Student("wangmazi",12);
        Student s4 = new Student("liuxiang",12);

        //添加到集合中
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);

        //用collections进行排序，因本身这是一个static修饰，就不需要进行创建对象了
        //这个方法实现排序的对象需要实现comparable接口，用第二种排序方法，就是匿名内部类，创建Comparator接口
        //public static <T> void sort(List<T> list,
        //                            Comparator<? super T> c)
        Collections.sort(array, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                //左边减右边，为升序
                int num = s1.getAge() - s2.getAge();  //年龄为首先条件
                int num2 = num == 0? s1.getName().compareTo(s2.getName()) : num;  //年龄相等，就比较姓名
                return num2;
            }
        });

        Iterator<Student> it = array.iterator();
        while(it.hasNext()) {
            Student s = it.next();
            System.out.println(s.getName() + ", " + s.getAge());
        }

        for (Student s : array) {
            System.out.println(s.getName() + ", " + s.getAge());
        }

    }
}
