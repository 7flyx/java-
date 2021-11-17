import java.util.*;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-17
 * Time: 18:31
 * Description:
 */

public class Code01_IsRotateWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        boolean isRotate = isRotateWord(str1, str2);
        System.out.println(isRotate? "YES" : "NO");
    }


    public static boolean isRotateWord(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        //str1是主串。先补齐相等的串，用KMP查询即可
        String s = str1 + str1;
        int[] next = getNext(str2);
        int i = 0;
        int j = 0;
        int N = s.length();
        int M = str2.length();
        while (i < N && j < M) {
            if (s.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == M;
    }

    public static int[] getNext(String str2) {
        int N = str2.length();
        int[] next = new int[N];
        next[0] = -1;
        next[1] = 0;

        int cn = 0;
        int index = 2; //从第三个字符开始判读
        while (index < N) {
            if (str2.charAt(cn) == str2.charAt(index - 1)) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}