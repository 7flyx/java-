import java.util.Random;
import java.util.Stack;

/**
 * Created by flyx
 * Description: 给定一个正数数组，数组中累积和与最小值的乘积，假设叫指标A。
 * 请返回子数组中，指标A的最大值？ 单调栈结构
 * User: 听风
 * Date: 2021-09-22
 * Time: 18:09
 */

public class HumdrumStack {
    public static void main(String[] args) {

        int[] array = {2,3,1,7,5};
        Record[] records = calcLRMinNum(array);
        System.out.println(calcMinSum(array, records));

    }



    private static class Record {
        public int left, right;

        public Record(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     *  计算给定数组中的每个数，返回每个数值，左右两边最近且比它本身小的值
     *  既然求两边的最小值，就需要用到 单调递减栈
     * @param array 原数组
     * @return 返回组成答案的数组
     */
    public static Record[] calcLRMinNum(int[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        Record[] res = new Record[array.length];
        Stack<Integer> stack = new Stack<>(); //假设题中，没有重复值。存储的下标值
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                int j = stack.pop(); //弹出当前破坏单调性的数值
                int k = stack.isEmpty()? -1 : stack.peek(); //弹出之后，新的栈顶。也就是原栈顶的下级结点
                res[j] = new Record(k, i);
            }
            stack.push(i);
        }

        //处理循环结束后，栈里还剩下的元素。即就是现栈里的元素，右边是没有比它小的数值
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty()? -1 : stack.peek();
            res[j] = new Record(k, array.length); //右边值没有
        }
        return res;
    }

    /**
     *  根据record数组计算子数组的最小值
     * @param array 原数组
     * @param record 每个数对应的左右两边的最小值。两个数之间的就是大于他本身的数。求和后再乘以这个本身的数即可
     * @return 子数组的总和，取最大值
     */
    public static int calcMinSum(int[] array, Record[] record) {
        if (array == null || record == null) {
            return 0;
        }

        //从左开始遍历数组
        int max = 0;
        for (int i = 0; i < record.length; i++) {
            int sum = 0;
            //遍历左右两个数值之间的所有数，累加和
            for (int j = record[i].left + 1; j < record[i].right; j++) {
                sum += array[j];
            }
            max = Math.max(max, sum * array[i]);
        }
        return max;
    }
}
