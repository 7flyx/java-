package demo1;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        //统计一个字符串字母的出现次数
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        //用HashMap集合进行存储，键就是字母，值就是次数
        HashMap<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);  //拿到字母
            Integer value = hm.get(key); //拿到值。如果返回的是null，说明没有
            if(value == null) {
                hm.put(key,1);
            } else {
                value++;
                hm.put(key,value);
            }
        }

        //拼接字符串
        StringBuilder sb = new StringBuilder();
        Set<Character> keyset = hm.keySet();  //将键组合成一个set集合
        for (Character key : keyset) {
            int value = hm.get(key);
            //拼接
            sb.append(key).append("(").append(value).append(")");
        }
        System.out.println(sb);
    }
}
