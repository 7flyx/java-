package demo2;

public class demo {
    public static void main(String[] args) {
        //测试类
        Student s = new Student("张三",20);
        System.out.println(s);  //如果student类中没有重写toString，这里输出的是这个类的地址值

        /*
         public void println(Object x) {
            String s = String.valueOf(x);
            synchronized (this) {
                print(s);
                newLine();
            }
           }

             public static String valueOf(Object obj) {
                return (obj == null) ? "null" : obj.toString();
            }

             public String toString() {
                return getClass().getName() + "@" + Integer.toHexString(hashCode());
            }
         */
    }
}
