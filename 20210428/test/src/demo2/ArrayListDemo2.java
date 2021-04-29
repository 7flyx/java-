package demo2;

import java.util.ArrayList;

public class ArrayListDemo2 {
    public static void main(String[] args) {
        //ArrayList类的一些操作
        ArrayList<String> array = new ArrayList<String>();
        array.add("hello");
        array.add("world");
        array.add("java");

        //移除，remove ，两种方式，一个是索引，另一个是元素
        //array.remove("hello"); //返回值Boolean类型
        //array.remove(1); //被删除的对象是谁，就返回谁

        //修改，set(int index,E e)---使用到索引值时，特别注意的是，不要越界了
        array.set(1,"hello"); //返回值为，被修改的是谁，就返回谁
        System.out.println("索引值为1::" + array.get(1)); //返回索引值为1的元素
        System.out.println("计算集合的元素个数:" + array.size());


        System.out.println(array);
    }
}
