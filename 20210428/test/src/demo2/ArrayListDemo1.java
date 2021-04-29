package demo2;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        //集合的概念---ArrayList<E>
        //底层就是一数组，只不过这个数组是可以变大小的，<E>，这里的E指的是集合里面的数据类型，泛型

        ArrayList<String> array = new ArrayList<String>();
        array.add("hello"); // public boolean add(E e), 在末尾添加元素
        array.add("world");
        array.add("java");

      //  array.add(1,"good"); //第二种添加方法，有索引值的，在索引值的位置添加数据
        //array.add(3,"good"); // public void add(int index, E element)
        //array.add(4,"good"); //在添加之前，集合的元素个数为3个，多索引值为0~2，
                                            //此时却在索引值为4的地方添加数据，出现越界访问的情况IndexOutOfBoundsException
        System.out.println("array:" + array); //[hello]  ,会自动添加左右括号
    }
}
