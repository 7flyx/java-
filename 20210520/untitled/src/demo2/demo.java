package demo2;

public class demo {
    public static void main(String[] args) {
//        int[] arr = {2, 2, 2, 0, 1};
//        int min = minArray(arr);
//        System.out.println(min);

//        int[] arr = {3,2,3,3,2,2,2,5,7,9,7,5,9};
//        int eor = 0;
//        for (int i = 0; i < arr.length; i++) {
//            eor ^= arr[i];
//        }
//        System.out.println("eor: " + eor);

        int a = 1137696976;
        int count = 0;
        while (a != 0) {
             count++;
             //a = a & (a -1);
            a = a ^ (a & ((~a) + 1));
//            //a & ((~a) + 1) 计算得到的是a二进制位中最右边的1

        }
        System.out.println("count: " + count);
    }

    public static int minArray(int[] numbers) {

        //二分查找的思想---既然是旋转后的数组，即就是两个有序的子数组
        //特殊情况，另当别论

        //3 4 5 1 2
        //一般情况下，第一个元素是大于等于最后一个元素的
        int left = 0;  //数组的第一个元素--3
        int right = numbers.length - 1; //数组的最后一个元素--2
        int mid = 0;
        while (left + 1 != right) { //两个下标值相差1时，此时的right就是指向的最小元素
            //新的问题---  1 1 1 0 1
            //如上面所述，当left 与right 与mid 三者指向的元素是一样大时，如何判断？
            //这种情况，就只能进行顺序查找了
            mid = (left + right) / 2;
            if (numbers[left] == numbers[right] && numbers[left] == numbers[mid]) {
                return MinInOrder(numbers, left, right);
            }

            if (numbers[left] <= numbers[mid]) { //说明mid指向的元素还是在第一个子数组中
                left = mid;
            } else if (numbers[right] >= numbers[mid]) {
                right = mid;
            }
        }

        return numbers[left] > numbers[right] ? numbers[right] : numbers[0];
    }

    public static int MinInOrder(int[] numbers, int left, int right) {
        //顺序查找即可
        int result = numbers[left];
        for (int i = left + 1; i < right; i++) {
            if (numbers[i] < result)
                result = numbers[i];
        }
        return result;
    }

    public static void selectSort(int[] arr) {
        //选择排序---每次循环找到当前数组中最小的元素，放到最前面
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            //首先保存当前需要被替换的元素下标
            int minIndex = i;
            for (int j = i + 1; j < arr.length; i++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            //交换数据
            swap(arr, i, minIndex);
        }
    }

    public static void BubbleSort(int[] arr) {
        //冒泡排序
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {  //躺数
            for (int j = 0; j < arr.length - 1 - i; j++) { //每一趟需要进行判断的对数
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        //插入排序---想象摸牌时的场景
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ 0
        // 0 ~ 1
        // 0 ~ 2
        // ....
        //初始化从1开始，将下标为0 的地方空出来
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1] ; j--) {
                //首先j不能越界，再者就是 arr[j] 与 arr[j+1] 之间的关系，j一直自减
                swap(arr,j,j+1);
            }
        }
    }

    public static void swap(int[] arr, int i, int minIndex) {
        int tmp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = tmp;
    }
}
