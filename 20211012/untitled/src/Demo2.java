import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-12
 * Time: 15:28
 * Description:
 */

class Student implements Comparable<Student>{
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

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name); //以姓名进行排序
    }
}

public class Demo2 {
    public static void main(String[] args) {
        Student student1 = new Student("Tom", 10);
        Student student2 = new Student("Emma", 5);
        Student student3 = new Student("Alice", 20);
        Student student4 = new Student("Kate", 30);

        Student[] array = new Student[4];
        array[0] = student1;
        array[1] = student2;
        array[2] = student3;
        array[3] = student4;

        System.out.println("排序前:" + Arrays.toString(array));
        Arrays.sort(array); //以姓名进行排序
        System.out.println("排序后:" + Arrays.toString(array));

    }
}
