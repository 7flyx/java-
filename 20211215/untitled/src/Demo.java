/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-15
 * Time: 15:31
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
//        int[] arr = {2, 5, 7, 10, 6, 5, 4, 3};
        int[] arr = {2, 3, 5,5};
        System.out.println(f(arr));
    }

    public static int f(int[] arr) {
        if (arr ==  null || arr.length < 2) {
            return -1;
        }

        int eor = 0;
        int tmp = 0; //同或结果
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        tmp = eor;
        System.out.println(eor);
        System.out.println(tmp);
        for (int i= 0; i < arr.length; i++) {
            int a = tmp ^ arr[i];
            if (((~a)  ) == (eor |(1 << 31))) {
                return arr[i];
            }
        }
        return -1;


    }
}
