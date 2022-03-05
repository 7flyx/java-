import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-02
 * Time: 15:26
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(getBiggestOne(n));
        }
    }

    public static int getBiggestOne(int n) {
        if (n == -1) {
            return 32;
        }
        int res = 0;
        int tmp = n;//临时保存n，用于计算是否到头了
        int index = 0; //左移位数
        int num = 0; //临时存储一个结果
        while (tmp != 0) {
            if ((n & (1 << index)) != 0) { //当前位置是1的情况
                num++;
                tmp -= tmp & (-tmp); //减去最靠右的1
            } else { //当前位置是0，结算前面的结果
                res = Math.max(res, num);
                num = 0;
            }
            index++; //左移位数++
        }
        res = Math.max(res, num);
        return res;
    }
}
