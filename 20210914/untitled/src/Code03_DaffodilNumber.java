/**
 * Created by flyx
 * Description: 水仙花数
 * User: 听风
 * Date: 2021-09-14
 * Time: 11:12
 */
public class Code03_DaffodilNumber {
    public static void main(String[] args) {
        //生成所有三位数之间的水仙花数
        for (int i = 100; i < 1000; i++) {
            int firstNum = i / 100;
            int secondNum = i / 10 % 10;
            int thirdNum = i % 10;
            if (Math.pow(firstNum, 3) + Math.pow(secondNum, 3) + Math.pow(thirdNum, 3) == i) {
                System.out.print(i + " ");
            }
        }
    }
}
