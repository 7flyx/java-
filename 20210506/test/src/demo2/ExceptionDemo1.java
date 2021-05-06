package demo2;

public class ExceptionDemo1 {
    public static void main(String[] args) {
        //异常处理
        System.out.println("开始");
        method();
        System.out.println("结束");
    }

    public static void method() {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[3]);
        } catch (ArrayIndexOutOfBoundsException e){ //ArrayIndexOutOfBoundsException 的一个对象 e
           // System.out.println("索引越界");
            e.printStackTrace();
        }
    }
}
/*
没有加try catch
    开始
    Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3
	at demo2.ExceptionDemo1.method(ExceptionDemo1.java:13)
	at demo2.ExceptionDemo1.main(ExceptionDemo1.java:7)

	加了try catch后
	开始
    结束
    java.lang.ArrayIndexOutOfBoundsException: 3
        at demo2.ExceptionDemo1.method(ExceptionDemo1.java:14)
        at demo2.ExceptionDemo1.main(ExceptionDemo1.java:7)
加了只后，就算程序那里会出错，但会输出错误信息，并往下执行程序

 */