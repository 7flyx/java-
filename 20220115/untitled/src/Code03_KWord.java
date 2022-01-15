/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 9:21
 * Description:前k个单词
 */
public class Code03_KWord {
    public String truncateSentence(String s, int k) {
        if (s == null || k <= 0) {
            return s;
        }

        char[] ch = s.toCharArray();
        int index = 0;
        while(index < ch.length && k > 0) {
            if (ch[index] == ' ') {
                k--;
            }
            if (k == 0) {
                break;
            }
            index++;
        }
        return new String(ch, 0, index);
    }
}
