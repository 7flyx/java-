package demo6;

//测试类
public class demo1 {
    public static void main(String[] args) {
        //提出老师学生的公共属性，定义到person类中，其余的非公共属性直接在自己的类中定义声明即可

        Teacher t = new Teacher();
        t.setName("飞人");
        t.setAge(90);
        System.out.println(t.getName() + "," + t.getAge());
        t.Teach();

        Student s = new Student("hhh",90);
        System.out.println(s.getName() + "," + s.getAge());
        s.Study();
    }
}
