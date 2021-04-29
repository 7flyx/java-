package demo3;

import demo2.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        //从键盘录入学生的信息数据，再存入集合中，再对集合进行遍历操作

        ArrayList<Student> array1 = new ArrayList<>();
        addStudent(array1);

        Student tmp = array1.get(0);
        System.out.println(tmp.getName() + "," + tmp.getAge());
    }

    public static void addStudent(ArrayList<Student> array) {
        Student s = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名:");

        s.setName(sc.nextLine());
        System.out.println("请输入学生年龄:");
        s.setAge(sc.nextInt());

        array.add(s);
    }
}
