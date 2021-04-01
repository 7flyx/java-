public class test2 {
    public static void main(String[] args) {
        //输出100以内，含有7的数和7的倍数的值
        for(int i = 1; i <= 100; i++) {
            if(i % 10 == 7 || i / 10 % 10 == 7 || i % 7 == 0) {
                System.out.println(i);
            }
        }
    }
}
