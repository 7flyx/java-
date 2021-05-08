package demo3;

import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        //Collection 集合下的 LinkedList
        LinkedList<String> l1 = new LinkedList<>();
        l1.add("hello");
        l1.add("world");
        l1.add("java");

        //遍历方式还是三种
        for (String s : l1) {
            System.out.println(s);
        }
        System.out.println(l1.pop()); //弹出第一个元素
        System.out.println(l1.pop()); //弹出第二个元素--弹出元素，也是弹出的第一个结点元素
        l1.push("javass");  //放在第一个位置--头插法

        System.out.println("---------");
        for(int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i));
        }
    }
}
