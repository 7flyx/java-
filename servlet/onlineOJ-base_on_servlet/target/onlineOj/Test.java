import java.util.Arrays;
import java.util.HashMap;
class Solution {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testTime = 2000; // 测试次数
        int length = 100; // 数组长度范围
        int range = 1000; // 数据范围
        for (int i = 0; i < testTime; i++) {
            int[] testData1 = generateArray((int)(Math.random() * length), range);
            int[] testData2 = Arrays.copyOf(testData1, testData1.length);
            int[] testAns = test(testData1);
            int[] userAns = solution.sortArray(testData2);
            int j = 0;
            for (; j < userAns.length; j++) {
                if (testAns[j] != userAns[j]) {
                    System.out.println("测试数据：" + Arrays.toString(testData2));
                    System.out.println("期望输出：" + Arrays.toString(testAns));
                    System.out.println("实际输出：" + Arrays.toString(userAns));
                    break;
                }
            }
            if (j == userAns.length && j != testAns.length) { // 两个结果数组长度不一样
                System.out.println("测试数据：" + Arrays.toString(testData2));
                System.out.println("期望输出：" + Arrays.toString(testAns));
                System.out.println("实际输出：" + Arrays.toString(userAns));
            }
        }


    }


    public static int[] test(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public static int[] generateArray(int length, int range) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random() * range) - (int)(Math.random() * range);
        }
        return arr;
    }
}
