package demo1;

import java.util.HashMap;

public class Code01_MinStickers {
    public static void main(String[] args) {
        //暴力递归
        /*
                给定一个字符串str，给定一个字符串类型的数组arr。
                arr里的每- - -个字符串，代表一张贴纸， 你可以把单个字符剪开使用，目的是
                拼出str来。
                返回需要至少多少张贴纸可以完成这个任务。
                例子: str= "babac"，arr = {"ba","c","abcd"}
                至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪
                开，含有2个a、2个b、1个C。是可以拼出str的。所以返回2。
         */

        String str = "babac";
        String[] arr = {"ba", "c", "abcd"};
        System.out.println(minStickers(str, arr));

    }

    public static int minStickers(String str, String[] arr) {
        if (str == null || arr == null || arr.length == 0) {
            return 0;
        }

        //将字符串贴纸arr，转换为数组的形式
        int N = arr.length;
        int[][] map = new int[N][26];
        for (int i = 0; i < map.length; i++) {
            char[] chs = arr[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                map[i][chs[j] - 'a']++;  //第i个字符串，这一行，对应的字符数值加1
            }
        }
        HashMap<String, Integer> dp = new HashMap<>(); //最终的答案，放入这个表
        dp.put("", 0); //当递归调用到最底层，就会是空串，返回0即可
        return process(str, map, dp);
    }

    public static int process(String rest, int[][] map, HashMap<String, Integer> dp) {
        if (dp.containsKey(rest)) {
            return dp.get(rest); //剩下的字符串rest，表中有，直接返回即可
        }

        int ans = Integer.MAX_VALUE;
        int[] tmap = new int[26]; //当前rest字符串转换为字符数组
        char[] target = rest.toCharArray();
        for (char ch : target) {
            tmap[ch - 'a']++; //将这个rest的字符串也转换为数组的形式
        }

        //循环遍历全部的帖纸，做相应的计算，进行递归调用
        for (int i = 0; i < map.length; i++) {
            if (map[i][target[0] - 'a'] == 0) { //如果当前贴纸，不能解决当前rest的任何一个字符，直接跳过
                continue;//至关重要： 拿到当前rest中的第一个字符，去看map中所有的字符串中是否有这个
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) { //大于0，说明还有这个字符
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        //最大值： 防止两者相减为负数的情况
                        sb.append((char)(j + 'a')); //先转换为StringBuilder
                    }
                }
            }

            String s = sb.toString();
            int tmp = process(s, map, dp);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp); //与原先的ans取最小值
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE? -1 : ans);
        return dp.get(rest);
    }
}
