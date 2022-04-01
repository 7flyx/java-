package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-01
 * Time: 15:03
 * Description:
 */

import java.util.*;

public class Code28_IgnoreList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String list = sc.nextLine();
            //sc.nextLine();
            String aim = sc.nextLine();
            List<String> arr = split(list);
            boolean isPrint = false;
            for (String s : arr) {
                if (s.equals(aim)) {
                    System.out.println("Ignore");
                    isPrint = true;
                    break;
                }
            }
            if (!isPrint) {
                System.out.println("Important!");
            }
        }
    }

    public static List<String> split(String str) {
        if (str == null) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        int N = str.length();
        boolean flag = false; // 是否已经有了左侧双引号了
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char ch = str.charAt(i);
            if (ch == '"' && !flag) {
                flag = true;
            } else if ((ch == '"' && flag) || (ch == ',' && !flag)) { // 已经有左侧双引号的情况，或者遇到了逗号，但是左侧没引号的情况
                if (sb.length()  != 0) {
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                    flag = false;
                }
            } else {
                sb.append(ch);
            }
        }
        list.add(sb.toString());
        return list;
    }

}
