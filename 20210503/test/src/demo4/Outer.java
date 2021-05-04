package demo4;

public class Outer {

    private int num = 10;
    //局部内部类
    public void method() {
        //int num = 20;
        class Inter {
            public void show() {
                System.out.println(num);
            }
        }

        //需要创建这个类的对象，进行调用
        Inter i = new Inter();
        i.show();
    }
}
