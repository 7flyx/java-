package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-26
 * Time: 11:02
 * Description: 3月26号 第一个代码题
 */
public class Code17_GetOnPaperChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();
            System.out.println(getOnPaperChar(str));
        }
    }

    public static String getOnPaperChar(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int n = str.length();
        int i = 0;
        while (i < n) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                sb.append(' ');
            } else if (ch == '<') {
              sb.append("<br/>");
              i += 4;
            } else {
                int tmp = (ch + 21);
                sb.append((char) (tmp > 90? (tmp % 91) + 65 : tmp));
            }
            i++;
        }
        return sb.toString();
    }

}
