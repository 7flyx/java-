package demo;

import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-01-09
 * Time: 15:57
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 1; j < N - 1 - i; j++) {
                if (arr[j - 1] % 2 == 1 && (arr[j] % 2 == 0 || arr[j] < arr[j - 1])) {
                    swap(arr, j - 1, j);
                } else if (arr[j - 1] % 2 == 0 && arr[j] % 2 == 0 && arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
