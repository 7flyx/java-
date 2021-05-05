package demo2;

public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //所有的类都直接或间接的继承object类，这里需要重写toString方法，用于直接调用这个的时，打印相应的数据信息
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //重写equals方法，此时再来调用这个方法，比较的就是类里面的内容
    @Override
    public boolean equals(Object o) {
        //this --- s1,  o ---- s2
        if (this == o) return true;
        //o 不为空指针（null），两个对象都来自同一个类 getClass
        if (o == null || getClass() != o.getClass()) return false;

        //向下转型
        Student student = (Student) o;
        //比较年龄
        if (age != student.age) return false;
        //比较姓名
        return name != null ? name.equals(student.name) : student.name == null;
    }

}
