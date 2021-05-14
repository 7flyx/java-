package demo2;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        //可变参数的使用-----类型通用符
        //为了表示List各种泛型的父类，可以使用通配符
        List<?> l1 = new ArrayList<>();
        List<? extends Number> l2 = new ArrayList<>();  //类型上限，仅限于Number及以下的类型
        List<? super Number> l3 = new ArrayList<>(); //类型下限，使用的类型必须高于Number，或者就是Number类型


        //可变参数
        System.out.println(sum(10,20,30,40));
    }

    public static int sum(int... a) {  //实则会将所接受的所有数据转换成数组
        int num = 0;
        for(int i : a) {
            num += i;
        }
        return num;
    }

    public static int sum1(int b, int... a) { //可变参数中的传参，有多个形参时，可变参数要放在最后
        return b;
    }
}
