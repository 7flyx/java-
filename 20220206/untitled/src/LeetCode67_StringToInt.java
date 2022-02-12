/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-12
 * Time: 21:18
 * Description:剑指offer 67 字符串转数字
 */
public class LeetCode67_StringToInt {
    public static int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] ch = str.toCharArray();
        int i = 0;
        while (i < ch.length && ch[i] == ' ') {
            i++;
        }
        //整个字符都是空格,或者第一个字符不是 数字+-
        if (i == ch.length || (ch[i] != '+' && ch[i] != '-' && (ch[i] < '0' || ch[i] > '9'))) {
            return 0;
        }

        boolean flag = ch[i] != '-'; //真 表示正数
        if (ch[i] == '+' || ch[i] == '-') {
            i++;
        }
        int res = 0; //用负数表示，因为负数要多一个
        for (; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                int num = ch[i] - '0';
                int tmp = res;
                res = res * 10 - (num);
                if ((res + (num)) / 10 != tmp || res > 0) { //跟上一次计算的结果不对了，就越界了
                    return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        if (res == Integer.MIN_VALUE && flag) {
            return Integer.MAX_VALUE;
        }
        return flag ? -res : res;
    }

    public static void main(String[] args) {
        System.out.println(strToInt("-2147483649"));
    }

}
