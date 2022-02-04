import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-04
 * Time: 10:24
 * Description:下一个更大的元素（单调栈结构）LeetCode496
 */
public class Code14_NextMaxNum {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length > nums2.length) {
            return new int[]{};
        }
        int[] res = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        //单调栈结构-从后遍历
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }
            int ans = stack.isEmpty()? -1 : stack.peek();
            map.put(nums2[i], ans); //将结果放入map中
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
