import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-25
 * Time: 20:15
 * Description:
 */
public class mapset {
    public static void main(String[] args) {
         int[] array = new int[10_0000];
         for (int i = 0; i < 10000; i++) {
             array[i] = (int)(Math.random() * 100000);
         }

//        System.out.println(firstRepeat(array));
//        HashSet<Integer> set = delRepeat(array);
//        Iterator<Integer> it = set.iterator();
//        while (it.hasNext()) {
//            System.out.print(it.next() + " ");
//        }
         Map<Integer, Integer> res = countRepeat(array);
        Set<Map.Entry<Integer, Integer>> entries = res.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println("数值: " + entry.getKey() + " 重复次数: " + entry.getValue());
        }

    }

    public static int firstRepeat(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.contains(array[i])) {
                set.add(array[i]);
            } else {
                return array[i];
            }
        }
        return -1; //没有重复值
    }

    public static HashSet<Integer> delRepeat(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.contains(array[i])) {
                set.add(array[i]);
            }
        }
        return set;
    }

    public static Map<Integer, Integer> countRepeat(int[] array) {
        if (array == null) {
            return null;
        }

        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> res = new HashMap<>(); //map记录所有重复的值以及次数
        for (int i = 0; i < array.length; i++) {
            if(!set.contains(array[i])) {
                set.add(array[i]);
            } else {
                if (!res.containsKey(array[i])) {
                    res.put(array[i], 2);
                } else {
                    res.put(array[i], res.get(array[i]) + 1);
                }
            }
        }
        return res;
    }
}
