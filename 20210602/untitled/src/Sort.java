public class Sort {
    public static void main(String[] args) {
        //堆排序---大根堆与小根堆
        //先对输入的数据放入一个数组，没插入进去就进行对排序，以大根堆为例，插到尾部，与他的父节点进行比较，比父节点大，就交换位置，循环
        //删除根结点，也是一样的，删除后，将最后一个结点的数据替换根结点，再往下进行判断左右孩子，把左右孩子大的一个进行替换
        int[] arr1 = {1, 5, 3, 2, 56, 8, 9, 45, 11, 65};

        heapSort(arr1);

        for (int i : arr1) {
            System.out.print(i + " ");
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i); // 大根堆的形式插入数组，没有另外建立数组，直接在原数组中进行数据的交换
        }

        //上面将数据以大根堆的形式插入数组，接下来就是将根结点的数据与数组最后的数据交换，然后对新根结点的数据做处理，往下层走
        //被交换到数组后面的数据就不在参与计算，这样排出来的就是升序遍历
        int heapSize = arr.length; //用于计算被交换后的数据个数
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize); //对新的根结点数据做下沉处理
            swap(arr, 0, --heapSize);
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        //对新的数据进行判断，做下沉处理
        int leftChild = index * 2 + 1; //先拿到左孩子的下标进行判断，如果左孩子都不存在，右孩子也就不存在
        while (leftChild < heapSize) {
            int largest = leftChild + 1 < heapSize && arr[leftChild + 1] > arr[leftChild] ?
                    leftChild + 1: leftChild; //返回左右孩子最大的结点下标
            largest = arr[largest] > arr[index]? largest : index; //判断最大孩子与父节点的大小
            if(largest == index) {
                break;
            }
            swap(arr,index,largest);
            index = largest; //index跟随往下走
            leftChild = index * 2 + 1; //再取左孩子
        }
    }

    public static void heapInsert(int[] arr, int index) {
        //如果下标为0 的位置作为根结点，则 左孩子 2*i + 1 ，右孩子 2*i + 2，父节点 （i-1） / 2
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int L, int R) {
        int tmp = arr[L];
        arr[L] = arr[R];
        arr[R] = tmp;
    }
}
