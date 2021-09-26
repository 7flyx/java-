import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-26
 * Time: 11:22
 * Description:
 */
public class MyArrayList<T> {
    private Object[] arr = {};
    private int size; //已经存储的元素个数
    public MyArrayList() {
        //最开始默认大小是10； JDK这个ArrayList底层，构造方法没给大小，而是在第一次add时，默认是10个大小
        arr = (T[])(new Object[10]);
    }

    public MyArrayList(int size) {
        arr = (T[])(new Object[size]); //给定的数组大小
    }

    public void add(T val) {
        if (this.size == this.arr.length) { //扩容的情况
            int newLength = this.size + (this.size >> 1); //1.5倍增长
            this.arr = Arrays.copyOf(this.arr, newLength);
        }
        this.arr[this.size++] = val;
    }

    public void addOfIndex(T val, int index) {
        if (this.size == this.arr.length) { //扩容的情况
            int newLength = this.size + (this.size >> 1); //1.5倍增长
            this.arr = Arrays.copyOf(this.arr, newLength);
        }
        if (index < 0 || index > this.size) {
            throw new RuntimeException("index is illegal.");
        }

        for (int i = this.size; i > index; i--) { //从后遍历，往后面的位置移动元素
            this.arr[i] = this.arr[i - 1];
        }
        this.arr[index] = val;
        this.size++;
    }

    public boolean contains(T val) {
        for (int i = 0; i < size; i++) {
            if (this.arr[i] == val) {
                return true;
            }
        }
        return false;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index is illegal.");
        }

        for (int i = index; i < size - 1; i++) {
            this.arr[index] = this.arr[index + 1];
        }
        this.size--;
    }

    public T search(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index is illegal.");
        }

        return (T)this.arr[index];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
