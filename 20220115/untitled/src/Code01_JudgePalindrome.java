/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-01-15
 * Time: 9:12
 * Description:判断是不是回文串
 */
public class Code01_JudgePalindrome {
    public boolean judge (String str) {
        // write code here
        if (str == null || str.length() < 2) return true;
        char[] ch = str.toCharArray();
        int left = 0;
        int right = ch.length - 1;
        while (left <= right) {
            if (ch[left] != ch[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
