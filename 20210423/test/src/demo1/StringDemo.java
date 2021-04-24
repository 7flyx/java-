package demo1;

public class StringDemo {
    public static void main(String[] args) {
        //字符串的构造方法
        //构造方法中，如果其他的变量的数据，也有一样的，虽然变量指向的内存地址不一样，但是内存里面的数据指向的就是第一次创建数据
        //的时候的内存地址
        String s1 = new String();  //无参类型
        System.out.println("s1:" + s1);

        //将字符数组变成字符串
        char[] chs = {'a','b','c'};
        String s2 = new String(chs); //传递字符数组类型的数据
        System.out.println("s2:" + s2);

        //将字节数组变成字符串
        byte[] bys = {97,98,99};  //存储的是数值
        String s3 = new String(bys);  //传递的是字节数组类型的数据
        System.out.println("s3:" + s3);  //此处会将对应的数值转换成字符类型

        //直接赋值类型的字符串--直接赋值类型，如果字符串的内容是一样的，在堆内存中，其他的变量都是自指向这一块空间，不会重新开辟新的空间
        //来存储相同的数据
        String s4 = "abc";
        System.out.println("s4:" + s4);
    }
}
