public class test2 {
    public static void main(String[] args) {
        //方法重载练习
        //根据类型的不同，进行调用，有时候数据类型需要强制类型转换


        System.out.println(compare(10,20));
        System.out.println(compare((short)10,(short)20));
        System.out.println(compare((byte)10,(byte)20));
        System.out.println(compare(10L,20L));

    }

    public static boolean compare(int a,int b) {
        System.out.println("int");
        return a == b;
    }

    public static boolean compare(short a,short b) {
        System.out.println("short");
        return a == b;
    }

    public static boolean compare(byte a,byte b) {
        System.out.println("byte");
        return a == b;
    }

    public static boolean compare(long a,long b) {
        System.out.println("long");
        return a == b;
    }
}
