import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-02
 * Time: 14:55
 * Description: 数组中出现奇数次的两个数
 */

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int[] res = getOnlyOne(array);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    public static int[] getOnlyOne(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }

        int ero = 0;
        for (int i = 0; i < array.length; i++) {
            ero ^= array[i];
        }

        //异或之后的结果肯定是奇数的两个数的结果
        int mostRight = ero & (~(ero - 1)); //拿到最右边的1
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            if ((mostRight & array[i]) != 0) {
                tmp ^= array[i];
            }
        }

        int[] response = {tmp, tmp ^ ero};
        if (response[0] > response[1]) {
            response[0] = tmp ^ ero;
            response[1] = tmp;
        }
        return response;

    }
}