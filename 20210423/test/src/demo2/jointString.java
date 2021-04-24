package demo2;

public class jointString {
    public static void main(String[] args) {
        //拼接字符串
        int[] arr = {1,2,3,4,5,6};
        System.out.println("s: " + joint(arr));

    }

    //特别值得注意的是，在main方法里调用方法，因为main是静态的，所以被调用的方法也必须是静态的
    //static简单点说：方便在没有创建对象的情况下来进行调用（方法/变量）
    public static String joint(int[] arr) {
        String s = "";
        s += "[";
        for (int i = 0; i < arr.length; i++) {  //此处的length是没有圆括号的
            if (i == arr.length - 1) {  //最后一个元素了
                s += arr[i];
            } else {
                s += arr[i];
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
