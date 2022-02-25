/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-25
 * Time: 20:43
 * Description: 剑指offer43 整数1 ~ n中，统计1出现的个数
 */
public class LeetCode43_OneOfNumber {
    class Solution {
        public int countDigitOne(int n) {
            if (n < 0) {
                return 0;
            }
            int high = n; //高位
            int low = 0; //低位
            int cur = 0; //当前位
            int res = 0; //答案 answer
            int digit = 1; //数位，表示当前cur的数位
            while(high != 0) {
                cur = high % 10; //拿到当前位置的数
                high /= 10; //高位减少一位
                if (cur == 0) {
                    res += high * digit;
                } else if (cur == 1) {
                    res += high * digit + low + 1;
                } else { //cur > 1的情况
                    res += (high + 1) * digit;
                }
                low = cur * digit + low;
                digit *= 10;
            }
            return res;
        }
    }
}
