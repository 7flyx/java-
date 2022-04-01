package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-31
 * Time: 9:28
 * Description:
 */
public class Code25_FormatName {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLine();
            }
            System.out.println(getFormatName(arr));
        }
    }

    public static String getFormatName(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].indexOf(" ") != -1 || arr[i].indexOf(",") != -1) {
                sb.append("\"" + arr[i] + "\""); // 加上双引号
            } else {
                sb.append(arr[i]);
            }
            if (i + 1 < arr.length) {
                sb.append(", "); // 姓名之间加上逗号和空格
            }
        }
        return sb.toString();
    }

}
