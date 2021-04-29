package demo2;

import java.util.ArrayList;

public class ArrayListDemo3 {
    public static void main(String[] args) {
        //集合的遍历操作
        ArrayList<String> array = new ArrayList<>();
        array.add("博尔特");
        array.add("苏炳添");
        array.add("张三丰");

        //根据类方法进行查看元素  get(索引值)
        //System.out.println(array.get(1));

        for ( int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }
}
