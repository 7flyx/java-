package demo3;

import java.util.Arrays;

public class arrayDemo {
    public static void main(String[] args) {
        //冒泡排序
        int[] arr = {20, 10, 33, 9, 11, 98};
       // System.out.println("排序前：" + arrattoString(arr));
        System.out.println("排序前：" + Arrays.toString(arr));

        /*
        for(int i = 0; i < arr.length - 1; i++) {  //决定躺数
            for(int j = 0; j < arr.length - 1 - i; j++) {  //每一趟需要比较的次数
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        */
        Arrays.sort(arr);

        //System.out.println("排序后：" + arrattoString(arr));
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println(Integer.MAX_VALUE);
    }

    public static String arrattoString(int[] arr) {
        //将数组的内容转换为字符串进行输出
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length -1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(", "); //链式
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
