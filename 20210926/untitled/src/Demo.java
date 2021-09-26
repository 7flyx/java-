import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-26
 * Time: 15:09
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
//        List<List<Integer>> res = generate(5);
//        System.out.println(res.toArray());
        System.out.println(getLengthOfSubString("abc1234321ab"));
        System.out.println(manacherStr("abc1234321ab"));
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows < 0) {
            return null;
        }
        if (numRows == 1) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            res.get(0).add(1);
            return res;
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < i + 1;j++) {
                if (res.get(i).size() == 0) {
                    res.get(i).add(1);
                } else if (res.get(i).size() == i) {
                    res.get(i).add(1);
                } else {
                    int number = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
                    res.get(i).add(number);
                }
            }
        }
        return res;
    }

    public static int getLengthOfSubString(String str) {
        if (str == null) {
            return 0;
        }

        char[] generateStr = generateString(str);
        int length = generateStr.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            int tmp = 1; //每个字符都能以自己本身的字符作为回文子串
            int radius = 1; //回文半径，也就是从i位置为中心，半径radius的范围内

            while (i - radius >= 0 && i + radius < length) { //左右两边都在数组的范围内，循环继续
                if (generateStr[i - radius] == generateStr[i + radius]) {
                    tmp += 2; //左右两个字符相等的情况
                    radius++; //回文半径加1
                } else {
                    break;
                }
            }

            max = Math.max(max, tmp); //判断当前的tmp是否是最长的回文子串
        }
        return max / 2; //因为我们比较的处理后的字符串，计算出的回文串要除以2.才是最终的答案
    }

    public static int manacherStr(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        char[] s = generateString(str);
        int length = s.length;
        int C = -1; //回文子串的中心点
        int R = -1; //最长回文子串的右边界
        int[] pArr = new int[length]; //回文半径数组
        int max = 0; //答案
        for (int i = 0; i < length; i++) {
            pArr[i] = i < R? Math.min(R - i, pArr[2 * C - i]) : 1; //判断i是否在R的范围内

            while (i - pArr[i] >= 0 && i + pArr[i] < length) {
                if (s[i - pArr[i]] == s[i + pArr[i]]) { //左右两边的字符
                    pArr[i]++; //回文半径加1
                } else {
                    break;
                }
            }

            //更新新的回文子串的右边界和 C中心点
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]); //判断是否是最长回文半径
        }

        return max - 1; //最终的答案，与max的值，相差1
    }

    public static char[] generateString(String str) {
        char[] res = new char[str.length() * 2 + 1]; //原2倍长度，再加1
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            //奇数位置放#，偶数位置放原字符
            res[i] = (i % 2) == 1? str.charAt(index++) : '#';
        }
        return res;
    }
}
