import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 10:37
 * Description:leetcode 剑指offer 039 直方图的最大面积
 */
public class LeetCode039_HistogramMaxArea {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int res = heights[0];
        list.add(heights[0]);
        for (int i = 1; i < heights.length; i++) {
            if ( list.size() != 0 && list.getLast() <= heights[i]) {
                list.add(heights[i]);
            } else {
                while (list.size() != 0 && list.getLast() > heights[i]) {
                    int tmp = list.getFirst();
                    res = Math.max(res, tmp * list.size());
                    list.removeFirst();
                }
                list.add(heights[i]);
            }
        }

        while (list.size() != 0) {
            int tmp = list.getFirst();
            res = Math.max(res, tmp * list.size());
            list.removeFirst();
        }
        return res;
    }




    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }


}
