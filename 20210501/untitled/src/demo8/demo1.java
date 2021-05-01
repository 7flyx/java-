package demo8;

public class demo1 {
    public static void main(String[] args) {
        //static修饰类的成员变量时，可以达到“共享”的作用，在这里其实跟C语言中的static有点相似
        //值得注意的是，static修饰的方法  只能去访问静态的方法和变量
        Student.school = "万州";
        Student s = new Student();
        s.name = "feiren";
        s.age = 20;
      //  s.school = "wanzhou";
        s.show();

        Student s2 = new Student();
        s2.name = "jjjj";
        s2.age = 30;
        //s2.school = "cq";  //在不进行初始化时，static修饰的变量会默认初始化为当前static中的值
        s2.show();
    }
}
