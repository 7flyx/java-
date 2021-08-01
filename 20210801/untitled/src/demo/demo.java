package demo;

import java.util.Arrays;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-01
 * Time: 19:37
 */
public class demo {
    public static void main(String[] args) {
//        int[] arr = createInitArray();
//        printArray(arr);


        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(myToString(array));
    }

    public static String myToString(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) {
                sb.append(arr[i]).append(", ");
            } else {
                sb.append(arr[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static int[] arrayCopy(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];
        //System.arraycopy(arr, 0, res, 0, arr.length); //底层是C/C++的代码写的，比较快
        //res = Arrays.copyOf(arr, arr.length);
        //res = arr.clone(); //克隆
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //二分查找，前提是数组有序，且知道是升序还是降序的情况下
    public static int binarySearch(int[] arr, int key) {
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static boolean isSorted(int[] arr) {
        if (arr == null) {
            return false;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void bobblingSort(int[] arr) {
        if (arr == null) {
            return;
        }

        boolean flag = true;
        for (int i = 0; i < arr.length - 1 && flag; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
        }
    }

    public static void swap(int[] arr, int L, int R) {
        int tmp = arr[L];
        arr[L] = arr[R];
        arr[R] = tmp;
    }

    public static int[] createInitArray() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
        }
    }

    public static void transform(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] <<= 1;
            }
        }
    }

    public static int sum(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int res = 0;
        for (int i : arr) {
            res += i;
        }
        return res;
    }

    public static double sum(double[] arr) {
        if (arr == null) {
            return 0.0;
        }
        double res = 0.0;
        for (double i : arr) {
            res += i;
        }
        return res;
    }

    public static double avg(int[] arr) {
        if (arr == null) {
            return 0.0;
        }
        double res = 0.0;
        for (int i : arr) {
            res += i;
        }
        return res / arr.length;
    }
}
