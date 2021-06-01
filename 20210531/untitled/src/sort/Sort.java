package sort;

public class Sort {
    public static void main(String[] args) {

        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] arr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr3 = {2, 1, 3, 5, 6, 7, 4, 6, 7, 8};
        //bubbleSort(arr1); //冒泡排序
        //selectSort(arr1); //选择排序
        //insertSort(arr1); //插入排序
        //mergeSort1(arr2); //归并排序（递归）
        //mergeSort2(arr1); //归并排序（非递归）
        quickSort(arr3); //快速排序

        for (int i : arr3) {
            System.out.print(i + " ");
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //冒泡排序--优化版
        boolean flag = true;
        for (int i = 0; i < arr.length - 1 && flag == true; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //选择排序---每次循环选择当前数组中最小的数据，把这个数据往前放
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i; //拿到最前面的数据位置
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;  //保存最小元素的下标 ,循环找到当前范围内最小的数据
                }
            }
            //进行数据的交换
            int tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;
        }
    }

    public static void insertSort(int[] arr) {
        //插入排序--往后面一个个的拿数据，小的话，就往前面放
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i]; //保存需要插入的值
            int j;
            for (j = i - 1; j >= 0 && arr[j] > insertValue; j--) { //倒着走
                arr[j + 1] = arr[j]; //前面的数据往后移动
            }
            if (j != i - 1) { //说明移动过--注意上面的循环停止时，j还是自减了一次
                arr[j + 1] = insertValue;
            }
        }
    }

    public static void mergeSort1(int[] arr) {
        //归并排序---二分递归传入参数，使左右两边的数组分别有序后，合并到一起
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void mergeSort2(int[] arr) {
        //归并排序---非递归---将数组分为一小块，将小块进行排序后，再去排序大块的
        int mergeSize = 1; //当前有序的，左数组的长度
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {  //循环一趟
                int M = L + mergeSize - 1; //中间值
                if (M >= N) { //中间值都在数组后面，则左边一定是有序的，不需要再进行merge
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break; //防止整形溢出
            }
            mergeSize <<= 1;
        }
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0; //指向help数组的
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //将数组重新赋值给arr
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //快速排序---荷兰国旗问题
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        //分为三个区，< 区   == 区    > 区
        if (L >= R) {
            return;
        }
        swap(arr,L+(int)(Math.random()*(R-L+1)), R); //随机将一个数交换到数组的最后，使时间复杂度变为O（N * log N）
        int[] M = netherlandsFlag(arr, L, R);
        process2(arr, L, M[0] - 1);
        process2(arr, M[1] + 1, R);
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        //将小于arr[R] 的数放到前面     等于他的数放到中间     大于他的数放到后面
        int less = L - 1;
        int more = R; //将R下标的值包含在右边区域，最后将这个数放到more区域的第一个数交换
        int index = L; //指向整个数组的开始，循环遍历数组，进行划分
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) { //放到 < 区
                swap(arr, index++, ++less); //先less后移，再调用swap函数交换数据，然后index++
            } else {  //放到 > 区
                swap(arr, index, --more); //先more前移，再调用swap函数交换数据，然后index不变，因为交换过来的数据还没有进行判断
            }
        }
        //将arr[R] 中的数据放到 more区的第一个
        swap(arr, R, more);
        // L......less  less+1.......more   more-1......R
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int L, int R) {
        int tmp = arr[L];
        arr[L] = arr[R];
        arr[R] = tmp;
    }
}
