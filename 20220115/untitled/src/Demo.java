import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 12:05
 * Description:
 */

import java.util.*;


class Person {
    private String name = "hello";
}

public class Demo extends Person {
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
        if (nums == null || k == 0) {
            return 0;
        }

        int res = 0;
        int num = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            num *= nums[right];
            while (num >= k) {
                num /= nums[left++];
            }
            res += (right - left + 1);
            right++;
        }

        return res;
    }

    public static void main2(String[] args) {
//        int[] arr = {1, 1, 1};
//        int k = 2;
//        System.out.println(numSubarrayProductLessThanK(arr, k));

        byte a = 127;
        byte b = 127;
        b += a;
        System.out.println(b);
        System.out.println(-5 + 1 / 4 + 2 * -3 + 5.0);
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


    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(delChar(str1, str2));
    }

    public static String delChar(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }

        char[] ch = str1.toCharArray();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        int len2 = str2.length();
        int len1 = str1.length();
        for (int i = 0; i < len2; i++) {
            set.add(str2.charAt(i));
        }

        for (int i = 0; i < len1; i++) {
            if (!set.contains(ch[i])) {
                sb.append(ch[i]);
            }
        }
        return sb.toString();

    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //队伍的数量
        int[] arr = new int[n * 3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        dfs(arr, new int[3], 0, 0);


    }

    public static float f() {
        long i = 100L;
        return i;
    }

    public static float f2() {
        long i = 100;
        return i;
    }

    public static int dfs(int[] arr, int[] tmp, int index, int len) {
        if (len == 3) {
            System.out.println(Arrays.toString(tmp));
            return 0;
        }
        if (index == arr.length) {
            return 0;
        }
        tmp[len] = arr[index];
        dfs(arr, tmp, index + 1, len + 1);
        dfs(arr, tmp, index + 1, len);
        return 0;
    }


    public static void main4(String[] args) {
        Scanner input = new Scanner(System.in);
        int a[] = new int[300000];
        while (input.hasNext()) {
            int n = input.nextInt();
            for (int i = 0; i < n * 3; i++) {
                a[i] = input.nextInt();
            }


            Arrays.sort(a, 0, 3 * n);
            int end = 3 * n - 1;
            int begin = 0;
            long sum = 0;
            while (end > begin) {
                sum = sum + a[end - 1];
                end = end - 2;
                begin = begin + 1;
            }
            System.out.println(sum);
        }
    }


    public static void main6(String[] args) {
        System.out.println(is("Admin"));

    }

    public static boolean is(String str) {
        return str.toLowerCase() == "admin";
    }

    public static void main7(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        int flag = 0;
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (data[i] > data[i - 1]) {
                if (flag == 0) {
                    flag = 1;
                }
                if (flag == -1) {
                    flag = 0;
                    result++;
                }
            } else if (data[i] < data[i - 1]) {
                if (flag == 0) {
                    flag = -1;
                }
                if (flag == 1) {
                    flag = 0;
                    result++;
                }
            }
        }


        System.out.println(result);
        System.out.println();
        scanner.close();

    }



    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2)-> {
            return (o2 - o1);
        });

    }


}


