/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-17
 * Time: 19:46
 * Description: 剑指offer64 ，计算阶乘
 */
public class LeetCode64_CalcFactorial {
    public int sumNums(int n) {
        boolean flag =  n > 1 && (n += sumNums(n - 1)) != 0;
        return n;
    }
}
