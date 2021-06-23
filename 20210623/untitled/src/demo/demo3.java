package demo;

import java.util.PriorityQueue;

public class demo3 {
    public static void main(String[] args) {
        //贪心算法3
        /*
            3. 一块金条切成两份，需要花费和金条长度数值一样的铜板
            比如： 长度为20的金条，不管怎么切，切一次就需要花费20个铜板。一群人想整分金条，问怎么切最省铜板
            例如： 给定数组【10,20,30】，代表有三个人，整块金条的长度为60，金条要分为10,20,30的长度。有以下的切法
                1. 10-50切一次，花费60个铜板。在20-30切一次，花费50个铜板，则共花费110个铜板
                2. 30-30切一次，花费60个铜板。在10-20切一次，花费30个铜板，则共花费90个铜板


                贪心算法的解题，一般都会用到 大根堆、小根堆或者排序算法
         */


    }

    public static int lessMoney(int[] money) {
        if (money == null) {
            return 0;
        }

        //先将数组中所有的元素，放入小根堆中，再二个元素为一组取出求和后，再放入小根堆，循环
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(); //本身这个优先队列就是小根堆，若需要大根堆，需要加比较器
        for (int i : money) {
            minQueue.add(i);
        }

        //哈夫曼树的思想
        int result = 0; //共花费了几个铜板
        int sum = 0; //临时存储小根堆弹出两个元素的和
        while (minQueue.size() > 1) { //还剩一个的时候，循环停止
            sum = minQueue.poll() + minQueue.poll();
            result += sum; //计算全部的非叶子结点数值，就是最后的答案
            minQueue.add(sum); //和，放入队列
        }
        return result;
    }
}
