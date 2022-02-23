/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-23
 * Time: 17:34
 * Description:剑指offer 20 判断字符串是不是数字
 */
public class LeetCode20_IsNumber {
    class Solution {
        public boolean isNumber(String s) {
            if (s == null) {
                return false;
            }
            char[] ch = s.trim().toCharArray();
            if (ch.length == 0) {
                return false;
            }
            int i = 0;
            boolean res = true; //答案
            if (ch[i] == '+' || ch[i] == '-') { //跳过加减号
                i++;
            }
            int cur = i;
            i = getNextIndex(ch, i); //跳转到下一组数字的末尾
            if (i == cur) { //表示小数点前没有数字
                res = false; //暂定false，看看下一位是不是小数点，且后面是否还有数字
            }
            if (i < ch.length && ch[i] == '.') { //有小数点的情况
                i++;
                cur = i; //暂存i的位置
                i = getNextIndex(ch, i); //往后跳转
                if (i == cur) {
                    //小数点后没有数字的情况,如果前面是真，就是真
                    ;
                } else {
                    res = true; //小数点后有数字的情况
                }
            }

            //e或E的情况
            if (i < ch.length && (ch[i] == 'e' || ch[i] == 'E')) {
                i++;
                if (i < ch.length && (ch[i] == '+' || ch[i] == '-')) {
                    i++;
                }
                cur = i; //暂存i的位置
                i = getNextIndex(ch, i);
                if (i == cur) { //表示e后面没有数的情况
                    res = false;
                } else {
                    //为真的情况，看前面的字符是否为真，是的话，就是真了
                    ;
                }
            }
            return i == ch.length && res;
        }

        //跳转到下一组数字的末尾
        private int getNextIndex(char[] ch, int index) {
            while(index < ch.length && ch[index] >= '0' && ch[index] <= '9') {
                index++;
            }
            return index;
        }
    }
}
