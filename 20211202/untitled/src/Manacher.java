import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-02
 * Time: 10:44
 * Description: 最长回文子串问题
 */

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] str = getSpecialStr(s);
        System.out.println(Arrays.toString(str));
        int R = -1;
        int C = -1;
        int[] pArr = new int[str.length];
        int maxLen = 0;
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            //先计算当前位置的回文半径值
            pArr[i] = i < R? Math.min(R - i, pArr[2 * C - i]) : 1;

            while(i - pArr[i] >= 0 && i + pArr[i] < str.length) {
                if (str[i - pArr[i]] == str[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            if (maxLen < pArr[i]) {
                maxLen = pArr[i];
                index = i;
            }
        }
        System.out.println(Arrays.toString(pArr));
        StringBuilder sb = new StringBuilder();
        //index是中心点，manLen是半径。两边减去半径。每次循环+=2即可
        for (int i = index - maxLen + 2; i < index + maxLen; i += 2) {
            sb.append(str[i]);
        }
        return sb.toString();
    }

    private char[] getSpecialStr(String s) {
        char[] ch = s.toCharArray();
        int N = ch.length;
        char[] str = new char[2 * N + 1];
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            str[i] = (i & 1) == 1? ch[index++] : '#';
        }
        return str;
    }

}


public class Manacher {
    public static void main(String[] args) {
        String s = "babad";
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome(s));
    }
}
