import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 12:05
 * Description:
 */

import java.util.*;

public class Demo {
    public static boolean heapInsert(int[] arr, int i) {
        while (i != 0) {
            int parent = (i - 1) / 2;
            if (arr[parent] < arr[i]) {
                int tmp = arr[parent];
                arr[parent] = arr[i];
                arr[i] = tmp;
                i = parent;
            } else if (arr[parent] == arr[i]) {
                return false;
            } else {
                break;
            }
        }
        return true;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums == null || k == 0) {
            return 0;
        }

        int res = 0;
        int num = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            num *= nums[right];
            while(num >= k) {
                num /= nums[left++];
            }
            res += (right - left + 1);
            right++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        int k = 2;
        System.out.println(numSubarrayProductLessThanK(arr, k));
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (!heapInsert(arr, i)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}

