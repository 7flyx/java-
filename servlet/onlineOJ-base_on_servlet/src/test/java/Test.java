import java.util.Arrays;
import java.util.HashMap;
public class Test {
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] arr = {2,7,11,15};
//        int target = 9;
//        int[] testAns = test(arr, target);
//        int[] userAns = solution.twoSum(arr, target);
//        if (testAns.length != userAns.length) {
//            System.out.println("答案错误");
//        } else {
//            boolean accurate = true;
//            for (int i = 0; i < testAns.length; i++) {
//                if (testAns[i] != userAns[i]) {
//                    accurate = false;
//                    break;
//                }
//            }
//            if(!accurate) {
//                System.out.println("测试数据：" + Arrays.toString(arr));
//                System.out.println("输出：" +Arrays.toString(userAns));
//                System.out.println("期望输出：" + Arrays.toString(testAns));
//            } else {
//                System.out.println("测试通过");
//            }
//        }
    }


    public static int[] test(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)) {
                return new int[] {i, map.get(target - x)};
            }
            map.put(x, i);
        }
        return new int[0];
    }
}
