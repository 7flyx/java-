package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-20
 * Time: 17:20
 * Description: 4月20号 第二个代码题 查找兄弟单词
 */
public class Code44_FindBrotherWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            String[] dirt = new String[n];
            for (int i = 0; i < n; i++) {
                dirt[i] = sc.next();
            }

            String str = sc.next();
            int k = sc.nextInt();

            List<String> list = findBrotherWord(dirt, str, k);
            Collections.sort(list); // 对顺序表进行排序
            System.out.println(list.size());
            if (list.size() >= k) {
                System.out.println(list.get(k - 1));
            }
        }
    }

    public static List<String> findBrotherWord(String[] dirt, String str, int k) {
        if (str == null || dirt == null || k <= 0) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        for (String s : dirt) {
            if (isBrotherWord(s, str)) {
                list.add(s); // s是str的兄弟单词，放到表中
            }
        }
        return list;
    }

    private static boolean isBrotherWord(String s, String str) {
        if (s.equals(str) || s.length() != str.length()) { // 两个单词相等，或长度不一样，都不是兄弟单词
            return false;
        }

        // 计数统计每个字符
        int[] count = new int[128];
        int n = s.length();
        int m = str.length();
        for (int i = 0; i < n; i++) { // 将s的字符全部统计出来
            int index = Integer.valueOf(s.charAt(i));
            count[index]++;
        }

        for (int i = 0; i < m; i++) {
            int index = Integer.valueOf(str.charAt(i));
            if (count[index]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
