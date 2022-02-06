import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 14:44
 * Description:LeetCode剑指offer40 返回最小的k个数
 */
public class LeetCode40_LeastNumber {
    class Solution {
        public int[] getLeastNumbers1(int[] arr, int k) {
            if (arr == null || arr.length <= k) {
                return arr;
            }
            if (k < 1) {
                return new int[]{};
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2)->o2 - o1);
            int size = 0;
            for (int num : arr) {
                if (size < k) {
                    heap.add(num);
                    size++;
                } else {
                    if (heap.peek() > num) {
                        heap.poll();
                        heap.add(num);
                    }
                }
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = heap.poll();
            }
            return res;
        }

        //bfprt解法，也就等价于计算第k小的数
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr == null || arr.length <= k) {
                return arr;
            }
            if (k < 1) {
                return new int[]{};
            }
            int kNum = bfprt(arr, 0, arr.length - 1, k - 1);
            return Arrays.copyOf(arr, k);
        }

        private int bfprt(int[] arr, int L, int R, int k) {
            if (L == R) {
                return arr[L];
            }
            int pivot = medianOfMedians(arr, L, R);
            int[] mid = process(arr, L, R, pivot);
            if (mid[0] <= k && k <= mid[1]) {
                return arr[k];
            } else if (k <= mid[0]) {
                return bfprt(arr, L, mid[0] - 1, k);
            } else {
                return bfprt(arr, mid[1] + 1, R, k);
            }
        }

        private int[] process(int[] arr, int L, int R, int pivot) {
            int less = L - 1;
            int more = R + 1;
            int index = L;
            while (index < more) {
                if (arr[index] < pivot) {
                    swap(arr, index++, ++less);
                } else if (arr[index] > pivot) {
                    swap(arr, index, --more);
                } else {
                    index++;
                }
            }
            return new int[]{less + 1, more - 1};
        }

        private int medianOfMedians(int[] arr, int L, int R) {
            int size = (R - L + 1) / 5;
            int surplus = (R - L + 1) % 5 == 0? 0 : 1;
            int[] tmp = new int[size + surplus];
            int index = 0;
            for (int i = 0; i < tmp.length; i++) {
                int start = L + i * 5;
                tmp[i] = sortAndMidNum(arr, start, Math.min(start + 5, R));
            }
            return bfprt(tmp, 0, tmp.length - 1, tmp.length >> 1);
        }

        private int sortAndMidNum(int[] arr, int L, int R) {
            for (int i = L; i < R; i++) {
                int min = i;
                for (int j = i + 1; j < R; j++) {
                    if (arr[min] > arr[j]) {
                        min = j;
                    }
                }
                if (min != i) {
                    swap(arr, i, min);
                }
            }
            return arr[(R + L) >> 1];
        }

        private void swap(int[] arr, int L, int R) {
            int tmp = arr[L];
            arr[L] = arr[R];
            arr[R] = tmp;
        }
    }
}
