package demo7;

public class demo {
    public static void main(String[] args) {
        //接口类同样的，不能进行实例化，需要用到多态的思想进行解决
       // Jumping j = new Jumping();  //不能进行实例化
        Jumping j = new Cat(); //多态的角度
        j.jump();
    }
}
