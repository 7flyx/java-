package demo3;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        //集合 arraylist测试
        //几个常用的方法
        ArrayList<String> a = new ArrayList<>();


        //添加
        a.add("hello");
        a.add(1,"world");
        a.add("good");
        a.add("javase");

        //删除
        a.remove("world");  //内容删除
        a.remove(0); //索引删除

        //查找
        a.contains("javase"); //值得注意的是，在C语言中判断一个元素是否有没有，只能用循环遍历，Java中直接调用方法

        //判断是否为空
        System.out.println(a.isEmpty());

        //清空
      //  a.clear();

        //元素个数
        System.out.println(a.size());
        System.out.println(a);

    }
}
