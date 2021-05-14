package demo4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ArrayListDemo {
    public static void main(String[] args) {
        //集合的嵌套使用，ArrayList与HashMap

        ArrayList<HashMap<String, String>> al = new ArrayList<>();

        //添加
        HashMap<String,String> hm1 = new HashMap<>();
        hm1.put("张无忌", "赵敏");
        hm1.put("令狐冲","任盈盈");

        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("飞人","飞人");
        hm2.put("博尔特","酥");

        //放入ArrayList
        al.add(hm1);
        al.add(hm2);

        //遍历
        for (HashMap<String,String> i : al) {
            Set<String> set = i.keySet(); // 拿到键
            for (String s : set) {
                System.out.println(s + ", " + i.get(s));
            }

        }
    }
}
