import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-22
 * Time: 19:59
 * Description:回文串分割问题-LeetCode-CC19
 * 给出一个字符串s，分割s使得分割出的每一个子串都是回文串
 * 计算将字符串s分割成回文分割结果的最小切割数
 * 例如:给定字符串s="aab",
 * 返回1，因为回文分割结果["aa","b"]是切割一次生成的。
 */
public class Code01_PalindromePartition {
    public int minCut (String s) {
        // write code here
        if (s == null || s.length() <= 1) {
            return 0;
        }

        char[] ch = s.toCharArray();
        int[] dp = new int[ch.length + 1];
        //用一张表存储同一个字符，多次出现的位置
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= ch.length; i++) {
            if (!map.containsKey(ch[i - 1])) {
                dp[i] = dp[i - 1] + 1;
                List<Integer> list = new ArrayList<>();
                list.add(i - 1);
                map.put(ch[i - 1], list);
            } else {
                List<Integer> list = map.get(ch[i - 1]);
                int size = list.size();
                dp[i] = dp[i - 1] + 1;
                for (int j = 0; j < size; j++) {
                    int preIndex = list.get(j);
                    if (sure(ch, preIndex + 1, i - 2)) { //注意两个边界值的范围
                        dp[i] =  Math.min(dp[i], dp[preIndex] + 1);
                    }
                }
                list.add(i - 1); //将当前字符的位置放入表中
            }
        }
        return dp[ch.length] - 1;
    }

    //判断范围内的字符串是不是回文串
    private boolean sure(char[] ch, int l, int r) {
        while (l <= r) {
            if(ch[l] != ch[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
