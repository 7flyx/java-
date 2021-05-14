package demo3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        //Map键存储 学校 ，值存储学生
        //Map<String, Student> map = new HashMap<String, Student>();
        Map<Student, String> map = new HashMap<>();

        Student s1 = new Student("飞人",30);
        Student s2 = new Student("酥饼",20);
        Student s3 = new Student("标枪",20);
        Student s4 = new Student("标枪",20);
        //键 是唯一的时候
//        map.put("北大",s1);
//        map.put("清华",s2);
//        map.put("重邮",s3);

        //键不是唯一的时候 --- 例如学生
        map.put(s1,"万州");
        map.put(s2,"重庆");
        map.put(s3,"石柱");
        map.put(s4,"北京");  //不重写Student类时，这里的键不会是唯一的，底层是Hash，重写Hashcode和equals即可

        //遍历方式
//        Set<String> set = map.keySet();
//        for (String s : set) {
//             Student t = map.get(s);
//            System.out.println(s + ", " + t.getName() + ", " + t.getAge());
//        }
//
//        Set<Map.Entry<String, Student>> me= map.entrySet();
//        for (Map.Entry<String,Student> m : me) {
//            String school = m.getKey();
//            Student t = m.getValue();
//            System.out.println(school + ", " + t.getName() + ", " + t.getAge());
//        }

        //遍历方式
        Set<Map.Entry<Student, String>> entry = map.entrySet();
        for(Map.Entry<Student, String> me : entry) {
            String location = me.getValue();
            Student s = me.getKey();  //me本身就是一个map集合，键值都有
            System.out.println(s.getName() + ", " + s.getAge() + ", " + location );
        }
    }
}
