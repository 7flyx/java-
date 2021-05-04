package demo3;

public class Outer {

    private int num = 10;

    //内部类
//    public class Inter {
//        public void show() {
//            System.out.println(num);
//        }
//    }

    private class Inter {  //一般将内部类设置为private ，再通过外部类的一个方法进行调用
        public void show() {
            System.out.println(num);
        }
    }

    public void method() {
        Inter i = new Inter();
        i.show();
    }
}
