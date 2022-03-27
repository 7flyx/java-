package demo;

import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-27
 * Time: 12:15
 * Description: LeetCode42 结雨水
 */
public class Code18_GetWater {
    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(arr));
    }

    public static int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int res = 0;
        Stack<Integer> stack = new Stack<>(); // 单调递增栈
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int mid = stack.pop(); // 当前底谷的高度
                // 计算当前能装水的高度
                int h = stack.isEmpty()? 0 :
                        Math.min(height[stack.peek()], height[i]) - height[mid];
                int sum = h * (i - (stack.isEmpty()? mid : stack.peek()) - 1);
                res += sum;
            }
            stack.push(i);
        }
        return res;
    }
}
