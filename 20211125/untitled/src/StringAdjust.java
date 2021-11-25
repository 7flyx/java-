import java.util.*;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-25
 * Time: 20:28
 * Description: 字符串调整
 * 给定一个字符串chas[],其中只含有字母字符和“*”字符，现在想把所有“*”全部挪到chas的左边，字母字符移到chas的右边。完成调整函数
 */

public class StringAdjust {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] ch = sc.nextLine().toCharArray();
        moveChar(ch);
        System.out.println(new String(ch));
    }

    public static void moveChar(char[] ch) {
        if (ch == null || ch.length <= 1) {
            return;
        }

        int N = ch.length;
        int pCh = N - 1;
        int pStar = N - 1;

        while (pCh >= 0 && pStar >= 0) {
            while (pCh >= 0 && ch[pCh] == '*') { //找字符
                pCh--;
            }
            while (pStar >= 0 && ch[pStar] != '*') { //找星号
                pStar--;
            }
            if (pStar < 0 || pCh < 0) break;
            if (pCh >= 0 && pStar >= 0 && pCh < pStar) {
                swap(ch, pCh, pStar);
                pStar--;
                --pCh;
            } else {
                pCh = pStar - 1;
            }
        }

    }

    public static void swap(char[] ch, int left, int right) {
        char tmp = ch[left];
        ch[left] = ch[right];
        ch[right] = tmp;
    }
}
