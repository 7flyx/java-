package demo1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        //set集合--不会出现重复的数据     且不能通过索引的方式去访问数据
        Set<String> set = new HashSet<>();

        set.add("hello");
        set.add("world");
        set.add("java");

        //不能通过索引的方式去访问,只能是增强for或者是迭代器
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        for (String s : set) {
            System.out.println(s); //不会保证数据的顺序的，无序的
        }

        //HashCode--哈希值---有的类型重写了hashcode方法，有的呢，是继承了Object类中，自己也可以重写这个方法
        System.out.println("hello".hashCode());
        System.out.println("world".hashCode());
        System.out.println(set.hashCode());

    }
}
