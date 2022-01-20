import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-20
 * Time: 20:08
 * Description:
 */
public class Demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = "aba";
        String str2 = "b";


        System.out.println(calcPalidormNum(str1, str2));
    }

    public static int calcPalidormNum(String str1, String str2) {
        if (str1 == null) {
            return 0;
        }
        if (str2 == null || str2.length() == 0) {
            return isPalindorm(str1)? 1 : 0;
        }

        int n = str1.length();
        char[] ch = str1.toCharArray();
        int res = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                res = isPalindorm(str2 + str1)? res + 1 : res;
            } else if (i == n) {
                res = isPalindorm(str1 + str2)? res + 1 : res;
            } else {
                res = isPalindorm(new String(ch, 0, i) + str2 + new String(ch, i, n - i))?
                        res + 1 : res;
            }
        }
        return res;
    }

    public static boolean isPalindorm(String str) {
        if (str == null || str.length() <= 1) {
            return true;
        }

        int left= 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
