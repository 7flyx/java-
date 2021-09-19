
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyx
 * Description: 给定两个字符串str和match，长度分别为N和M。实现一个算法，
 * 如果字符串str中含有子串match，则返回match在str中的开始位置，
 * 不含有则返回-1若出现了多次，则按照升序输出所有出现位置
 * User: 听风
 * Date: 2021-09-19
 * Time: 21:46
 */

public class Code02_KMP {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String m = in.readLine();
        ArrayList<Integer> res = new ArrayList<>();
        getIndexOf(s,m,res);

        for (Integer num : res) {
            System.out.print(num + " ");
        }
        in.close();
    }

    public static void getIndexOf(String s, String m, List<Integer> res) {
        if (s == null || m == null || s.length() < 1 || m.length() < 1) {
            return;
        }
        int[] next = getNextArr(m);
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i = 0;
        int j = 0;
        while (i < str1.length && j < str2.length) {
            if (j == -1) {
                j = 0;
            }
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++; //子串走到头了，主串往后走一步
            } else {
                j = next[j];
            }

            if (j == str2.length) { // 当子串的长度只有一个字符的时候，有可能会回溯到-1的位置
                res.add(i - j);
                j = next[j - 1];
            }
        }


        if (res.size() == 0) {
            res.add(-1);
        }
    }

    private static int[] getNextArr(String m) {
        if (m.length() < 2) {
            return new int[] {-1};
        }
        if (m.length() < 3) {
            return new int[] {-1, 0};
        }
        char[] str = m.toCharArray();
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2; //从第2个字符开始
        int cn = 0; //前缀字符串开始的地方，初始化为0，即就是第一个字符的位置
        while (index < str.length) {
            if (str[index - 1] == str[cn]) {
                next[index++] = ++cn;
            } else if (cn > 0) { //再次找上一组的前缀字符串
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}