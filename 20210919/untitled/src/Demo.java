import java.util.Scanner;

/**
 * Created by flyx
 * Description: 折纸问题
 * 请把一张纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，
 * 即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，
 * 从上到下依次是下折痕、下折痕和上折痕。给定一个输入参数N，代表纸条都从下边向上方连续对折N次，
 * 请从上到下打印所有折痕的方向。
 * User: 听风
 * Date: 2021-09-19
 * Time: 9:14
 */

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        process(n, 0, "down");
    }

    public static void process(int n, int cur, String flag) {
        if (n == cur) {
            return;
        }

        process(n, cur + 1, "down");
        System.out.println(flag);
        process(n, cur + 1, "up");
    }
}