package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-01-07
 * Time: 21:57
 * Description: 给定一个字符串，只含数字。请返回其所有的逆序数的个数。（线代中的逆序数）
 */
public class Code02_ReverseNumber {
    public static void main(String[] args) {
        String str = "362154";
        System.out.println(countReverseNumber(str));
    }

    public static int countReverseNumber(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return mergeSort(chars, 0, chars.length - 1);
    }

    private static int mergeSort(char[] chars, int L, int R) {
        if (L == R) {
            return 0;
        }
        int index = ((R - L) >> 1) + L;
        int ans = 0;
        ans += mergeSort(chars, L, index);
        ans += mergeSort(chars, index + 1, R);
        ans += merge(chars, L, index, R);
        return ans;
    }

    private static int merge(char[] chars, int L, int mid,  int R) {
        char[] help = new char[R - L + 1];
        int l = mid;
        int r = R;
        int ans = 0;
        int index = 0;
        while (l >= L && r > mid) { // 从右往左走。本身就是升序的状态
            if (chars[l] > chars[r]) {
                ans += r - mid;
                l--;
            } else { // 相等的时候，也是r--。这样才不会错过当前l位置的数
                r--;
            }
        }
        l = L;
        r = mid + 1;
        while (l <= mid && r <= R) {
            help[index++] = chars[l] > chars[r]? chars[r++] : chars[l++]; // 依然保持稳定性
        }
        while (l <= mid) {
            help[index++] = chars[l++];
        }
        while (r <= R) {
            help[index++] = chars[r++];
        }
        // 数据填回去
        while (--index >= 0) {
            chars[L + index] = help[index];
        }
        return ans;
    }

    private static void swap(char[] chars, int L , int R) {
        char tmp = chars[L];
        chars[L] = chars[R];
        chars[R] = tmp;
    }
}
