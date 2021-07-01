package demo1;

public class demo2 {
    public static void main(String[] args) {
        //背包模型
        /*
               给定两个长度都为N的数组weights和values，
               weights【i】 和values【i】 代表 i号物品的重量和价值
               给定一个正数bag，表示一个承重为bag的袋子
               你装的物品不能超过这个重量
               返回你能装的最大价值是多少
         */

    }

    public static int countValues(int[] weight, int[] values, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1; //重量已经超了，说明这个方案不可行
        }
        if (index == weight.length) {
            return 0;
        }
        //当前这个位置的物品要与不要的抉择
        int p1 = countValues(weight, values, index + 1, alreadyW, bag); //不要当前这个位置的物品
        int p2Next = countValues(weight, values, index + 1, alreadyW + weight[index], bag); //要了
        int p2 = -1;
        if (p2Next != -1) {
            p2 = values[index] + p2Next; //当前这个位置的物品的价值，加上后面的
        }
        return Math.max(p1, p2);
    }

    //rest表示还剩下多少空间
    public static int countValues(int[] weight, int[] value, int index, int rest) {
        if (rest < 0) {
            return -1; //说明这个方案不可行
        }
        if (index == weight.length) {
            return 0; //说明已经没有物品了
        }
        int p1 = countValues(weight, value, index + 1, rest); //不要当前位置的物品
        int p2 = -1;
        int p2Next = countValues(weight, value, index + 1, rest - weight[index]);
        if (p2Next != -1) {
            p2 = value[index] + p2Next;
        }
        return Math.max(p1, p2);
    }
}
