import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-20
 * Time: 19:06
 * Description:
 */
public class Demo {

    public static void main(String[] args) {
        int[] arr = {1,5,4,6,7,9,3,2};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(array.length - 1);

        int left, right;
        while (!stack.isEmpty()) {
            right = stack.pop();
            left =stack.pop();
            int p = partition(array, left, right);
            if (p > left + 1) {
                stack.push(left);
                stack.push(p - 1);
            }
            if (p < right - 1) {
                stack.push(p + 1);
                stack.push(right);
            }
        }
    }

    private static int partition(int[] array, int L, int R) {
        int tmp = array[L];
        while (L < R) {
            while (L < R && array[R] > tmp) {
                R--;
            }
            array[L] = array[R];

            while (L < R && array[L] < tmp) {
                L++;
            }
            array[R] = array[L];
        }
        array[L] = tmp;
        return L;
    }

    /**
     * 面试的时候，面试官现场让同学写的一个题
     * 将两个有序数组  合并成 一个有序数组
     * @param array1 [1,5,8,10]   m
     * @param array2 [3,4,9]      n
     * @return [1,3,4,5,8,9,10]
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(m+n)
     */
    public static int[] mergeArray(int[] array1,int[] array2) {
        if(array1 == null || array2 == null) {
            return array1 != null? array1 : array2;
        }

        int N = array1.length;
        int M = array2.length;
        int[] response = new int[N + M];
        int i = 0; //指向response
        int index1 = 0; //指向array1
        int index2 = 0; //指向array2
        while (index1 < N && index2 < M) {
            response[i++] = array1[index1] <= array2[index2]?
                    array1[index1++] : array2[index2++];
        }

        while (index1 < N) {
            response[i++] = array1[index1++];
        }
        while (index2 < M) {
            response[i++] = array1[index2++];
        }
        return response;
    }
}

