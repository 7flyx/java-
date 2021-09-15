import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by flyx
 * Description: 测试comparable、comparator、cloneable
 * User: 听风
 * Date: 2021-09-15
 * Time: 20:43
 */

/**
 *  Comparable, 实现没被排序的类中，需要重写compareTo方法，比较的是字典序
 *  如果需要修改比较规则，则需要修改类中的比较方法。对类的侵入性比较强。
 **/
class Student implements Comparable<Student>{
    public String name;
    public int age;
    public double score;

    public Student(String name, int age, double score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age; //重写比较方法
    }
}

class Manager {
    public String name;
    public int age;
    public int height;

    public Manager(String name, int age, int height) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

//这个类想实现深拷贝，还是得重写clone方法
class Money implements Cloneable{
    public int number;

    public Money(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Money{" +
                "number=" + number +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

//此处的Cloneable只是起到一个标志的作用，Cloneable接口里没有任何方法
//克隆的话，需要使用Object类中的clone方法
//若想实现深拷贝，每个对象里面的引用类型的成员变量需要重写clone的方法
class Person implements Cloneable{
    public String name = "feiren";
    public int a = 10; //普通类型
    public Money money = new Money(20);

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", a=" + a +
                ", money=" + money +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person tmp = (Person) (super.clone()); //想实现这个类的引用类型的深拷贝，还得继续拷贝引用类型，且需要连接上
        tmp.money = (Money)(this.money.clone());
        return tmp;
    }
}

public class Demo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        Person person1 = (Person) person.clone(); //需要强制类型转换，和抛出clone方法的异常问题
        System.out.println(person.toString());
        System.out.println(person1.toString());
        System.out.println("=================");
        person1.a = 20;
        person1.money.number = 1000;
        System.out.println(person.toString());
        System.out.println(person1.toString());


    }

    //比较器，单独实现的一个类，用于写比较方法的。直接将该类的实例对象传递出去即可
    private static class AgeCompare implements Comparator<Manager> {
        @Override
        public int compare(Manager o1, Manager o2) {
            return o1.age - o2.age;
        }
    }

    private static class HeightCompare implements Comparator<Manager> {
        @Override
        public int compare(Manager o1, Manager o2) {
            return o1.height - o2.height;
        }
    }

    public static void main2(String[] args) {
         Manager[] managers = new Manager[3];
         managers[0] = new Manager("feiren", 20, 170);
         managers[1] = new Manager("gaobo", 30, 180);
         managers[2] = new Manager("feiren", 25, 165);

        System.out.println("排序前：" + Arrays.toString(managers));

        //Arrays.sort(managers, new AgeCompare());
        Arrays.sort(managers, new HeightCompare());

        System.out.println("排序后：" + Arrays.toString(managers));
    }

    public static void main1(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("feiren", 20, 99.2);
        students[1] = new Student("boerte", 30, 95);
        students[2] = new Student("subintian", 29, 92);

        System.out.println(Arrays.toString(students));

        Arrays.sort(students); //非基本数据类型比较时，需要实现比较接口

        System.out.println(Arrays.toString(students));

    }
}
