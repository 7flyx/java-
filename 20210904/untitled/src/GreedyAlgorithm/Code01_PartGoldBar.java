package GreedyAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by flyx
 * Description:  一块金条切成两半，是需要花费和长度数值一-样的铜板的。 比如长度为20的金
 *              条，不管切成长度多大的两半，都要花费20个铜板。
 *              一群人想整分整块金条，怎么分最省铜板?
 *                  例如,给定数组{10, 20, 30}，代表一共三个人， 整块金条长度为10+20+30=60. .
 *              金条要分成10, 20, 30三个部分。如果先把 长度60的金条分成10和50，花费60;
 *              再把长度50的金条分成20和30，花费50;一共花费110铜板。
 *              但是如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20,
 *              花费30;一共花费90铜板。
 *               输入一个数组，返回分割的最小代价。
 * User: 听风
 * Date: 2021-09-04
 * Time: 8:43
 */
public class Code01_PartGoldBar {
    public static void main(String[] args) {
        int[] array = {10,20,30};
        System.out.println(partGoldBar(array));
    }

    public static class GoldCompare implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static int partGoldBar(int[] array) {
        //数组里存储的是需要分割出来的长度，我们以哈夫曼树的思想进行解题
        //将全部的数据放入小根堆，再两个一起弹出求和，再放入，最后队列还剩的最后一个就是最后的总代价
        if (array == null) {
            return 0;
        }
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i : array) {
            pQ.add(i);
        }
        int res = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            res += cur;
            pQ.add(cur);
        }
        return res;
    }
}
