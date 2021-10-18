import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-15
 * Time: 20:00
 * Description:
 */
public class InsertSort {
    public static void insertSort(int[] array) {
        if(array == null || array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int insert = array[i];
            for (; j >= 0; j--) {
                if (insert < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = insert;
        }
    }

    public static void shell(int[] array,int gap) {
        for(int i = gap - 1; i < array.length; i++) {
            int insert = array[i];
            int j = i - gap;
            for (; j >= 0 && array[j] > insert; j -= gap) {
                array[j + gap] = array[j];
            }
            array[j + gap] = insert;
        }
    }

    /**
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        if (array == null) return;
        int gap = array.length-1;//3
        while (gap > 1) {
            shell(array,gap);
            gap = gap/2;//1
        }
        shell(array,1);
    }

    public static void main(String[] args) {
        int[] array = {6,2,1,7,5};
        System.out.println(Arrays.toString(array));
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}


