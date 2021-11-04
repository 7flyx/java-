package demo;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-04
 * Time: 14:37
 * Description:
 */

class Array {
    int[] arr = new int[10_0000];
    int[] arr2 = new int[10_0000];
    Random random = new Random();

    Array() {
        for (int i = 0; i < arr.length; i++) {
            int tmp = random.nextInt(10_0001);
            arr[i] = tmp;
            arr2[i] = tmp;
        }
    }
}

class QuickSort {
    void quickSort(int[] arr) {
        long before = System.currentTimeMillis();
        quick(arr, 0, arr.length - 1);
        long after = System.currentTimeMillis();
        System.out.println("quickSort time: " + (after - before));
    }

    private void quick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        if (right - left <= 11) {
            int gap = arr.length - 1;
            while (gap > 0) {
                for (int i = gap; i < arr.length; i++) {
                    int tmp = arr[i];
                    int j = i - gap;
                    for (; j >= 0 && arr[j] > tmp; j -= gap) {
                        arr[j + gap] = arr[j];
                    }
                    arr[j + gap] = tmp;
                }
                gap >>= 1;
            }
        } else {
//            medianOfThree(arr, left, right);
//            while (left < right) {
//                int pivot = partition(arr, left, right);
//                quick(arr, left, pivot - 1);
//                left = pivot + 1;
//            }

            int index = left + (int)((right - left) * Math.random());
            swap(arr, index, right);
            while (left < right) {
                int[] pivot = partition2(arr, left, right);
                quick(arr, left, pivot[0] - 1);
                left = pivot[1] + 1;
            }
        }
    }

    //荷兰国旗问题优化
    private int[] partition2(int[] array, int L, int R) {
        if (L == R) {
            return new int[] {L, L};
        }

        int less = L - 1;
        int more = R;
        int index = L;
        while (index <  more) {
            if (array[index] > array[R]) {
                swap(array, --more, index);
            } else if (array[index] < array[R]) {
                swap(array, ++less, index++);
            } else {
                index++;
            }
        }
        swap(array, more, R);
        return new int[] {less + 1, more};
    }

    private void medianOfThree(int[] arr, int left, int right) {
        int mid = (left + right) >> 1;
        if (arr[mid] > arr[left]) {
            swap(arr, mid, left);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
    }

    private void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    private int partition(int[] arr, int left, int right) {
        int tmp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= tmp) {
                --right;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= tmp) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = tmp;
        return left;
    }
}


class QuickSort2 {
    public void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        long start = System.currentTimeMillis();
        quick(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("quickSort2: " + (end - start));
    }

    private void quick(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int index = left + (int)((right - left) * Math.random());
        swap(array, index, right);
        int[] mid = partition(array, left, right);
        quick(array, left,mid[0] - 1);
        quick(array, mid[1] + 1,right);
    }

    private int[] partition(int[] array, int L, int R) {
        if (L == R) {
            return new int[] {L, L};
        }

        int less = L - 1;
        int more = R;
        int index = L;
        while (index <  more) {
            if (array[index] > array[R]) {
                swap(array, --more, index);
            } else if (array[index] < array[R]) {
                swap(array, ++less, index++);
            } else {
                index++;
            }
        }
        swap(array, more, R);
        return new int[] {less + 1, more};
    }


    private void swap(int[] array, int L, int R) {
        int tmp = array[L];
        array[L] = array[R];
        array[R] = tmp;
    }
}

public class Demo {

    private static Array array = new Array();

    private static void test() {
        Array tmp1 = new Array();
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(tmp1.arr);
        //System.out.println(Arrays.toString(tmp1.arr));

        System.out.println("==========");

        new QuickSort2().quickSort(tmp1.arr2);
        //System.out.println(Arrays.toString(tmp2.arr));

    }

    public static void main(String[] args) {
//        int[] arr = {0, 9, 1, 8, 2, 7, 3, 6, 4, 5, 5};
//        new QuickSort().quickSort(arr);
//        System.out.println(Arrays.toString(arr));
        test();
    }
}

