package demo2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class demo2 {
    public static void main(String[] args) {
        //并发异常分析
        List<String> l = new ArrayList<>();
        l.add("hello");
        l.add("world");
        l.add("java");

        //迭代器遍历
        Iterator<String> it = l.iterator(); //集合里面
        while(it.hasNext()) {
            String s = it.next();  //ConcurrentModificationException
            if (s.equals("world")) {
                l.add("javase");
            }
        }
    }
}
