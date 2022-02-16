/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-16
 * Time: 21:21
 * Description:剑指offer45 将数组中的数排列之后，变为最小的数
 */
public class LeetCode45_ArrayToMinNum {
    class Solution {
        public String minNumber(int[] nums) {
            if (nums == null) {
                return "";
            }
            String[] s = new String[nums.length];
            for (int i = 0; i < nums.length; i++) { //先全部转换为字符串
                s[i] = String.valueOf(nums[i]);
            }
            //快速排序
            quickSort(s, 0, nums.length - 1);
            StringBuilder sb = new StringBuilder();
            for (String str : s) {
                sb.append(str);
            }
            return sb.toString();
        }

        private void quickSort(String[] s, int L, int R) {
            if (L >= R) {
                return;
            }

            int index = L + (int)(Math.random() * (R - L));
            swap(s, R, index); //与最后一个元素进行交换
            int[] mid = process(s, L, R); //荷兰国旗问题
            quickSort(s, L, mid[0] - 1);
            quickSort(s, mid[0] + 1, R);
        }

        private int[] process(String[] s, int L, int R) {
            int less = L - 1;
            int more = R;
            int index = L;
            while (index < more) {
                if ((s[index] + s[R]).compareTo(s[R] + s[index]) < 0) {
                    swap(s, index++, ++less); //往前放
                } else if ((s[index] + s[R]).compareTo(s[R] + s[index]) > 0) {
                    swap(s, index, --more); //往后放
                } else {
                    index++;
                }
            }
            swap(s, R, more);
            return new int[]{less + 1, more};
        }


        private void swap(String[] nums, int l, int r) {
            String tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
    }
}
