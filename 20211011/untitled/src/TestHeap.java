import java.util.Arrays;

public class TestHeap {

    public int[] elem;
    public int usedSize;

    public TestHeap() {
        this.elem = new int[10];//10个0
    }

    public void shiftDown(int parent) {
        int leftChild = 2 * parent + 1;

        while (leftChild < this.usedSize) {
            int largest = leftChild + 1 < this.usedSize && this.elem[leftChild + 1] > this.elem[leftChild] ?
                    leftChild + 1 : leftChild; //获取左右孩子的最大值

            if (this.elem[parent] < this.elem[largest]) {
                int tmp = this.elem[parent];
                this.elem[parent] = this.elem[largest];
                this.elem[largest] = tmp;
            }

            parent = largest;
            leftChild = 2 * parent + 1;
        }
    }

    //1 4 18 7 .....
    public void createBigHeap(int[] array) {
        //把需要的数据 存放到elem当中 一会儿建堆的时候 直接操作 elem数组就可以了
        for (int i = 0; i < array.length; i++) {
            this.elem[i] = array[i];
            this.usedSize++;
        }

        for (int i = (this.usedSize - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }


    public boolean isFull() {
        return this.usedSize == this.elem.length;
    }

    public void push(int val) {
        if (isFull()) {
            this.elem = Arrays.copyOf(this.elem, 2 * this.elem.length);
        }
        this.elem[this.usedSize] = val;
        this.usedSize++;//
        //向上调整
        shiftUp(this.usedSize - 1);
    }

    public void shiftUp(int child) {
        int parent = (child - 1) / 2;
        while (parent >= 0 && this.elem[parent] < this.elem[child]) {
            int tmp = this.elem[parent];
            this.elem[parent] = this.elem[child];
            this.elem[child] = tmp;
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    public int poll() {
        if (this.usedSize == 0) {
            throw new RuntimeException("size is empty.");
        }

        int tmp = this.elem[0];
        this.elem[0] = this.elem[--this.usedSize];
        this.elem[this.usedSize] = tmp;

        shiftDown(0); //向下调整
        return tmp;
    }
}

