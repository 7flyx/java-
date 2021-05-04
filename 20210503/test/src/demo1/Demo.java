package demo1;

//测试类
public class Demo {
    public static void main(String[] args) {

        PingPangPlayer pp = new PingPangPlayer();
        pp.setName("马龙");
        pp.setAge(30);
        System.out.println(pp.getName() + ", " + pp.getAge());
        pp.eat();
        pp.speak();
        pp.study();
    }
}
