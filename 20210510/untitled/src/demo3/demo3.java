package demo3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class demo3 {
    public static void main(String[] args) {
        //map集合的使用---map<key, value>

        Map<String,String> map = new HashMap<>(); //底层还是哈希表

        //添加元素
        map.put("键","值");
        map.put("张国荣","帅帅帅");
        map.put("博尔特","快");
//        map.put("博尔特","很快");  //当集合中已经有键时，后面添加的数值，会把前面的覆盖掉 --  博尔特=很快

        //删除
        map.remove("键");  //返回的是值
        Set<String> set = map.keySet();  //将所有的键 ，放入set集合中  -- 不可重复的元素
        Collection<String> values = map.values(); //将所有的值放入Collection集合中 --- 可以重复的元素

        for (String s : set) {
            System.out.println(s);
        }

        for (String s : values) {
            System.out.println(s);
        }


        System.out.println(map);
    }

    public class Student {
    }
}
