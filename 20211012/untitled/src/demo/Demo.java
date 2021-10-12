package demo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-12
 * Time: 14:53
 * Description:
 */

class Student {
    public String name;
    public int age;
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + this.age;
    }
}

public class Demo {

    private static class AgeCompare implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age; //以年龄进行排序
        }
    }

    public static void main(String[] args) {
        Student student1 = new Student("小明", 10);
        Student student2 = new Student("小刚", 5);
        Student student3 = new Student("彭于晏", 28);
        Student student4 = new Student("胡歌", 26);

        Student[] array = new Student[4];
        array[0] = student1;
        array[1] = student2;
        array[2] = student3;
        array[3] = student4;
        System.out.println("排序前: " + Arrays.toString(array));

        Arrays.sort(array, new AgeCompare());
        System.out.println("排序后: " + Arrays.toString(array));
    }
}
