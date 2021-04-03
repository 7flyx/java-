package test1;

public class test1 {
    public static void main(String[] args) {

        //百钱白鸡算法案例
//        for (int x = 0; x <= 20; x++) {
//            for (int y = 0; y <= 33; y++) {
//                int z = 100 - x - y;
//                if (z % 3 == 0 && 5 * x + y * 3 + z/3 == 100)
//                    System.out.println(x + "," + y + "," + z);
//            }
//        }


        //100分钱，换购1分 3分  5分，问共有几种换法,三种币值，至少各有一个
        int count = 0;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 33; j++) {
                int z = 100 - 5 * i -3 * j;
                if (i * 5 + j * 3 + z == 100 && z >= 1) {
                    count++;
                    System.out.println(i + "," + j + "," + z);
                }
            }
        }
        System.out.println("共有"+count+"种换购算法");
    }
}
