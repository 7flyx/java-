package demo1;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        //由链表和哈希表组成的---输入的时候是什么顺序，输出的时候就是什么顺序
        LinkedHashSet<String> linkedHash = new LinkedHashSet<>();

        linkedHash.add("hello");
        linkedHash.add("world");
        linkedHash.add("java");

        Iterator<String> it = linkedHash.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());  //按照输入时的顺序进行输出
        }

        String s = new String();

        int a = 10;
        String s1 = s.valueOf(a);
        System.out.println(s1);
    }
}
