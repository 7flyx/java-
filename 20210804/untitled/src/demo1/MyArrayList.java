package demo1;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-04
 * Time: 10:03
 */

public class MyArrayList {
    public int[] elem;//elem=null
    public int usedSize;
    public static final int capacity = 10;

    public MyArrayList() {
        this.elem = new int[capacity];
        //this.usedSize = 0;
    }

    public boolean isEmpty() {
        return usedSize == capacity;
    }

    // 打印顺序表
    public void display() {
        for (int i = 0; i < usedSize; i++) {
            System.out.print(elem[i] + " ");
        }
    }

    // 在 pos 位置新增元素
    public void add(int pos, int data) {
        if (pos >= elem.length || pos < 0 || usedSize == elem.length || pos != usedSize) {
            throw new RuntimeException("arrayList have already been full. or pos is illegality");
        }
        for (int i = usedSize - 1; i >= pos; i--) {
            elem[i + 1] = elem[i];
        }
        elem[pos] = data;
        usedSize++;
    }

    // 判定是否包含某个元素
    public boolean contains(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if (elem[i] == toFind) {
                return true;
            }
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int search(int toFind) {
        for (int i = 0; i < usedSize; i++) {
            if (elem[i] == toFind) {
                return i;
            }
        }
        return -1;
    }

    // 获取 pos 位置的元素
    public int getPos(int pos) {
        if (pos < 0 || pos >= usedSize) {
            //throw new RuntimeException("pos is illegality");
            return -1;
        }
        return elem[pos];
    }

    // 给 pos 位置的元素设为 value
    public void setPos(int pos, int value) {
        if (pos < 0 || pos >= usedSize) {
            throw new RuntimeException("pos is illegality");
        }
        elem[pos] = value;
    }

    //删除第一次出现的关键字key
    public void remove(int toRemove) {
        for (int i = 0; i < usedSize; i++) {
            if (elem[i] == toRemove) {
                for (int j = i; j < usedSize && j + 1 < capacity; j++) {
                    elem[j] = elem[j + 1];
                }
                usedSize--;
                break;
            }
        }
    }

    // 获取顺序表长度
    public int size() {
        return usedSize;
    }

    // 清空顺序表
    public void clear() {
        //也可以修改usedSize，置为0，也可以
        elem = new int[capacity];
    }
}


