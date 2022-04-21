package demo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-21
 * Time: 9:55
 * Description: 4月21号 第二个代码题 简单错误记录
 */
public class Code45_SimpleErrorCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // LinkedHashMap就是在HashMap基础之上，添加一些功能
        // 做到了 先插入的数据，在顺序遍历时，肯定是先遍历到的。也就是先来先打印
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        while (sc.hasNext()) {
            String str = sc.next();
            int lineNum = sc.nextInt();
            String[] arr = str.split("\\\\");  //根据\切割
            String s = arr[arr.length - 1];
            if (s.length() > 16)  //截取
                s = s.substring(s.length() - 16);
            String key = s + " " + lineNum;
            if (map.containsKey(key))
                map.put(key, map.get(key) + 1);
            else {
                map.put(key, 1);
            }
        }
        int count = 0;
        for (String string : map.keySet()) {
            count++;
            if (count > (map.keySet().size() - 8)) //输出最后八个记录
                System.out.println(string + " " + map.get(string));
        }
    }
}
