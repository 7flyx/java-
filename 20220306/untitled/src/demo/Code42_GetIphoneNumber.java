package demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-18
 * Time: 14:23
 * Description: 4月18号 第一个代码题 电话号码
 */
public class Code42_GetIphoneNumber {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.next());
            sc.nextLine();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLine();
            }

            List<String> iphoneNumber = getIphoneNumber(arr);
            for (String s : iphoneNumber) {
                System.out.println(s);
            }
            System.out.println();
        }

    }

    public static List<String> getIphoneNumber(String[] arr) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        char[] tmp = new char[8]; // 临时存储号码
        for (int i = 0; i < arr.length; i++) {
            int index = 0; // 指向tmp数组
            if (arr[i].length() < 7) {
                continue;
            }

            int n = arr[i].length();
            int j = 0;
            for (; j < n && index != 8; j++) {
                if (index == 3) {
                    tmp[index++] = '-';
                }
                char ch = arr[i].charAt(j);
                if (ch >= '0' && ch <= '9') {
                    tmp[index++] = ch;
                } else if (ch >= 'A' && ch <= 'Z') {
                    tmp[index++] = getNumOfChar(ch);
                }
            }
            if (index == 8) {
                String s = new String(tmp);
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); // 字典序排序
            }
        });
        return list;
    }

    private static char getNumOfChar(char ch) {
        if (ch >= 'A' && ch <= 'C') {
            return '2';
        } else if (ch >= 'D' && ch <= 'F') {
            return '3';
        } else if (ch >= 'G' && ch <= 'I') {
            return '4';
        } else if (ch >= 'J' && ch <= 'L') {
            return '5';
        } else if (ch >= 'M' && ch <= 'O') {
            return '6';
        } else if (ch >= 'P' && ch <= 'S') {
            return '7';
        } else if (ch >= 'T' && ch <= 'V') {
            return '8';
        }
        return '9';
    }

}
