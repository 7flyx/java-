import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-18
 * Time: 18:56
 * Description:
 */
public class Demo {

    public static void main(String[] args) {
        String str = "1.1";
        String str2 = "1.10";
        String[] arr = str.split("\\.");
        String[] arr2 = str2.split("\\.");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].compareTo(arr2[i]));
        }
    }

    public String name="abc";
    public static void main2(String[] args){
        Demo test=new Demo();
        Demo testB=new Demo();
        System.out.println(test.equals(testB)+","+test.name.equals(testB.name));
    }

    public static void main1(String[] args) {
        int[] array = {1,7,5,3,2,5,7,9};
        quickSort(array);

        int i=0;
        Integer j = new Integer(0);
        System.out.println(i==j);
        System.out.println(j.equals(i));



        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }

            if (min != i) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
    }

    public static int partition(int[] array,int start,int end) {
        int tmp = array[start];
        while (start < end) {
           while (start < end && array[end] >= tmp) {
               end--;
           }
           array[start] = array[end];

           while (start < end && array[start] <= tmp) {
               start++;
           }
           array[end] = array[start];
        }
        array[end] = tmp;
        return end;
    }

    public static void quick(int[] array,int left,int right) {
        if(left >= right) {
            return;
        }

        medianOfThree(array, left, right);
        int pivot = partition(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot+1,right);
    }

    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }

    public static void medianOfThree(int[] array,int left,int right) {
        int mid = (left+right)/2;

        //array[mid] <= array[start] <= array[right]
        if(array[left] > array[mid]) {
            int tmp = array[left];
            array[left] = array[mid];
            array[mid] = tmp;
        }
        if (array[mid] > array[right]) {
            int tmp = array[mid];
            array[mid] = array[right];
            array[right] = tmp;
        }

        //在以上两个if后，是已经有序的情况了，此时将中间值放到第一个位置处即可
        if (array[left] < array[mid]) {
            int tmp = array[left];
            array[left] = array[mid];
            array[mid] = tmp;
        }
    }

}
