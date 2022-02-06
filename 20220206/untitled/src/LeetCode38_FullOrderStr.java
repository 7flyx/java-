import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-06
 * Time: 15:59
 * Description:字符串的排列
 */
public class LeetCode38_FullOrderStr {

    private static List<String> list;

    public static String[] permutation(String s) {
        if (s == null) {
            return new String[]{};
        }
        list = new ArrayList<>();
        dfs(s.toCharArray(), 0);
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static void dfs(char[] ch, int index) {
        if (index == ch.length) {
            list.add(new String(ch));
            return;
        }
        for (int i = index; i < ch.length; i++) {
            swap(ch, index, i);
            dfs(ch, index + 1);
            swap(ch, index, i);
        }
    }

    private static void swap(char[] ch, int l, int r) {
        char tmp = ch[l];
        ch[l] = ch[r];
        ch[r] = tmp;
    }

    public static void main(String[] args) {
        String s = "abc";
        String[] permutation = permutation(s);
        System.out.println(Arrays.toString(permutation));
    }

}
