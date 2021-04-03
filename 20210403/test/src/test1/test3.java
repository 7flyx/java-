package test1;

public class test3 {
    public static void main(String[] args) {

        //数组交换数据
        int[] arr = {11, 22, 33, 44, 55};

        reverse(arr);
        Print(arr);
    }

    public static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void Print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
