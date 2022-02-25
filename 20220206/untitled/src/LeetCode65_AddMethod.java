/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-25
 * Time: 19:28
 * Description: 剑指offer65 不使用四则运算，做加法
 */
public class LeetCode65_AddMethod {
    class Solution {
        public int add(int a, int b) {
            while (b != 0) {
                int c = (a & b) << 1; //进位的数
                a = a ^ b; //异或的值
                b = c; //进位的数不为0，循环继续
            }
            return a;
        }
    }
}
