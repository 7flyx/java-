import java.util.*;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-17
 * Time: 21:28
 * Description:
 */

public class Code02_IsTransformWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nums = sc.nextLine();
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        System.out.println(isTransFormWord(str1, str2));
    }

    public static boolean isTransFormWord(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        int[] map = new int[256];
        int N = str1.length();
        int M = str2.length();
        for (int i = 0; i < N; i++) {
            map[str1.charAt(i)]++;
        }
        for (int i = 0; i < M; i++) {
            map[str2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
