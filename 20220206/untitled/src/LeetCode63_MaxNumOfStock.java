import java.util.LinkedList;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-11
 * Time: 19:43
 * Description:leetcode剑指offer63 股票的最大利润
 */
public class LeetCode63_MaxNumOfStock {
    //单调栈结构-O(N^2)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        LinkedList<Integer> list = new LinkedList<>(); //单调递减栈
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            while (list.size() != 0 && list.getLast() >= prices[i]) {
                if (list.size() > 1) { //至少有两个元素的情况
                    res = Math.max(res, list.getLast() - list.getFirst());
                }
                list.removeLast(); //删除最后一个元素
            }
            list.add(prices[i]); //队尾添加新的元素
        }
        if (list.size() > 1) {
            int numMax = list.pollLast();
            while (list.size() != 1) {
                list.pollLast();
            }
            int numMin = list.pollLast();
            res = Math.max(res, numMax - numMin);
        }
        return res;
    }

    //最优解-也就是一个动态规划的题，进行了空间压缩
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int pre = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            pre = Math.min(pre, prices[i]); //找i之前的最小值
            res = Math.max(res, prices[i] - pre); //与当前值做差
        }
        return res;
    }
}
