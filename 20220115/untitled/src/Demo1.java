import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-20
 * Time: 20:08
 * Description:
 */

class Deom {
    public Deom(String s) {
        System.out.println("B");
    }

    public Deom() {

    }
}

public class Demo1 extends Deom{
    public Demo1(String s) {
        System.out.println("D");
    }

    public static void main(String[] args) {
        new Demo1("c");
    }


    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = "aba";
        String str2 = "b";


//        System.out.println(calcPalidormNum(str1, str2));
        System.out.println(StrToInt("+2147483647"));
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

    public static int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] ch = str.toCharArray();
        char flag = '+';
        int res = 0;
        if (ch[0] == '-') {
            flag = '-';
        }
        for (int i = (ch[0] == '+' || ch[0] == '-')? 1 : 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                if (res > 0) {
                    res = -res;
                }
                res = res * 10 - (ch[i] - '0');
            } else {
                return 0;
            }
        }
        return flag == '-'?  res : -res;
    }
}
