package demo5;

/*
    测试类
 */
//public class son extends father,mother{  //Java中，一个类不能继承多个类
//    public static void main(String[] args) {
//
//    }
//}

public class son extends father {  //只能继承一个类，但是能继承多层类，例如father类可以继承grandfather类
    //此时，son类同时拥有father类中的drink方法  和  grandfather 类中的smoker方法
}