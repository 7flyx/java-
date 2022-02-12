/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-12
 * Time: 20:28
 * Description:剑指offer63 实则就是约瑟夫环问题
 */
public class LeetCode62_CycleSurplusNum {
    class Solution {
        public int lastRemaining1(int n, int m) {
            if (n == 1) {
                return 0;
            }
            //(旧环的生存者 + m) % 当前长度
            return (lastRemaining(n - 1, m) + m) % n;
        }

        public int lastRemaining(int n, int m) {
            int x = 0;
            //长度为2的时候开始推算
            for (int i = 2; i <= n; i++) {
                x = (x + m) % i;
            }
            return x;
        }

    }


}
