package GreedyAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by flyx
 * Description: 输入:
 *              正数数组costs
 *              正数数组profits
 *              正数k
 *              正数m
 *          含义:
 *              costs[i]表示i号项目的花费
 *              profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 *              k表示你只能串行的最多做k个项目
 *              m表示你初始的资金
 *          说明:
 *              你每做完一-个项目，马.上获得的收益，可以支持你去做下一-个项目。
 *          输出:
 *              你最后获得的最大钱数。
 * User: 听风
 * Date: 2021-09-04
 * Time: 9:01
 */
public class Code02_MaxProfit {
    public static void main(String[] args) {

    }

    private static class Node {
        public int cost;
        public int profit;

        public Node(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class CostCompare implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost; //升序
        }
    }

    public static class ProfitCompare implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.cost; //降序
        }
    }

    public static int calcMaxProfits(int[] costs, int[] profit, int k, int m) {
        if (costs == null || profit == null || k < 1) {
            return 0;
        }

        PriorityQueue<Node> costPQ = new PriorityQueue<>(new CostCompare()); //放入全部的数据，满足m初始资金，才弹出
        PriorityQueue<Node> profitPQ = new PriorityQueue<>(new ProfitCompare()); //弹出的项目，按利润排序
        int size = costs.length;
        for (int i = 0; i < size; i++) {
            costPQ.add(new Node(costs[i], profit[i]));
        }
        int res = m;
        for (int i = 0; i < k; i++) {
            while (!costPQ.isEmpty() && costPQ.peek().cost < m) {
                profitPQ.add(costPQ.poll());
            }
            if (profitPQ.isEmpty()) { //启动资金不足，没项目可做
                break;
            }
            res += profitPQ.poll().profit;
        }
        return res;
    }
}


