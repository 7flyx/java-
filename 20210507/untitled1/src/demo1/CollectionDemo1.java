package demo1;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionDemo1 {
    public static void main(String[] args) {
        //collection 集合的迭代器
        //Iterator<E> iterator()    接口
        //里面的两个方法 next（）与hasNext（）
        ArrayList<String> a = new ArrayList<>();
        a.add("hello");
        a.add("world");
        a.add("java");

        System.out.println(a);
        Iterator<String> it = a.iterator(); //迭代器是在集合里面的一个方法

        /*
        System.out.println(it.next()); //hello
        System.out.println(it.next()); //world
        System.out.println(it.next()); //java
        System.out.println(it.next()); //noSuchElementException  异常
        */
         while (it.hasNext()) {  //返回True 或False
             String s = it.next();
             System.out.println(s);
         }

    }
}
