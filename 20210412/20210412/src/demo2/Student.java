package demo2;

public class Student {
    private String name;
    private int age;  //private修饰，不能在外部函数直接秀海数值，必须在这个方法内定义一个方法，

    //构造方法--- public 类名()
    public Student() {
        System.out.println("无参构造方法");
    }
    public Student(String name) {
        this.name = name;
    }

    //方法
    public void setName (String name) {
//        name = name;  //当此处的形参名与上面的变量重合时，需要使用this关键字，或者是形参名重新取
        this.name = name;  //this修饰的参数名为变量名，而非形参
    }

    public void setAge (int age) {
        this.age = age;
    }

    public void show () {
        System.out.println(name + "," + age);
    }

}
