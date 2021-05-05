package demo1;

public class SystemDemo {
    public static void main(String[] args) {
        //测试类   --System
//        System.out.println("开始");
//        System.exit(0);  //0，代表正常退出，exit 退出Java虚拟机
//        System.out.println("结束");

        //时间戳----currentTimeMillis()--单位毫秒
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时:" + (end - start) + "毫秒");

    }
}
