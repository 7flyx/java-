import java.util.Scanner;

/**
 * Created by flyx
 * Description:  猜数字
 * User: 听风
 * Date: 2021-09-14
 * Time: 11:04
 */
public class Code02_GuessNumber {
    public static void main(String[] args) {
        int number = (int)(Math.random() * 100);
        Scanner sc = new Scanner(System.in);
        System.out.println("=======猜数字游戏======");
        while (true) {
            System.out.print("请输入: ");
            int guess = sc.nextInt();
            if (guess == number) {
                System.out.println("恭喜你猜对了");
                break;
            } else if(guess > number) {
                System.out.println("你猜大了");
            } else {
                System.out.println("你猜小了");
            }
        }

    }
}
