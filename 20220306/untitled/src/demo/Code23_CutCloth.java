package demo;

import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-30
 * Time: 14:04
 * Description:
 */
public class Code23_CutCloth {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] arr = sc.nextLine().split(" ");
            System.out.println(curCloth(arr[0], arr[1]));
        }
    }

    public static int curCloth(String str, String t) {
        if (str == null || t == null) {
            return 0;
        }

        int index = 0;
        int res = 0;
        while (index < str.length()) {
            int tmp = str.indexOf(t, index);
            if (tmp != -1) {
                res++;
            } else {
                break;
            }
            index = t.length() + tmp;
        }
        return res;
    }


}
