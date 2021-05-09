package demo2;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class demo3 {
    public static void main(String[] args) {
        //用TreeSet集合完成学生成绩排序
       // Set<Student> se = new HashSet<>();
        TreeSet<Student> se = new TreeSet<Student>(new Comparator<Student>() {

            public int compare(Student s1, Student s2) {
                int num = s1.getSum() - s2.getSum();
                int num2 = num == 0? s1.getName().compareTo(s2.getName()) : num;
                return num2;
            }
        });


        Student s1 = new Student("z三", 90,90);
        Student s2 = new Student("l四", 90,90);
        Student s3 = new Student("王麻子",60,50);

        se.add(s1);
        se.add(s2);
        se.add(s3);

        for (Student s : se) {
            System.out.println(s.getName() + ", " + s.getChinese() +", " + s.getMath());
        }
    }
}
