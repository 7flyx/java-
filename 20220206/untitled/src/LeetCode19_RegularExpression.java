/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-28
 * Time: 23:35
 * Description: 正则表达式匹配
 */
public class LeetCode19_RegularExpression {
    static class Solution {
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) {
                return false;
            }
            // return process(s.toCharArray(), 0, p.toCharArray(), 0);
            return process3(s.toCharArray(), p.toCharArray());
        }

        private boolean process(char[] s, int indexOfS, char[] p, int indexOfP) {
            //两个串都结尾了
            if (indexOfS == s.length && indexOfP == p.length) {
                return true;
            }
            //模式串结尾了
            if (indexOfS != s.length && indexOfP == p.length) {
                return false;
            }

            //下一字符是*的情况
            if (indexOfP + 1 < p.length && p[indexOfP + 1] == '*') {
                if (indexOfS < s.length && (s[indexOfS] == p[indexOfP] || p[indexOfP] == '.')) { //可以相等的情况
                    return process(s, indexOfS + 1, p, indexOfP + 2) ||
                            process(s, indexOfS + 1, p, indexOfP) || //.替换当前值，串往后走就行，因为*能够构造.
                            process(s, indexOfS, p, indexOfP + 2); //不替换当前值，后续再去递归
                } else { //不相等的情况
                    return process(s, indexOfS, p, indexOfP + 2); // *抹除这不相等的字符，后续字符递归
                }
            }

            //下一个字符不是*的情况
            if (indexOfS < s.length && (s[indexOfS] == p[indexOfP] || p[indexOfP] == '.')) {
                return process(s, indexOfS + 1, p, indexOfP + 1); //当前字符相等 或者能替换的情况
            }
            return false;// 既不相等，也不能替换，只能是false
        }

        //经典dp
        private boolean process2(char[] s, char[] p) {
            int row = s.length;
            int col = p.length;
            boolean[][] dp = new boolean[row + 1][col + 1]; //能够达到index = s.length的情况
            //填写base case
            dp[row][col] = true; //indexOfS = s.length && indexOfP = p.length
            // for (int i = 0; i < row; i++) {
            //     dp[i][col] = false; //最右一列，全是false，Java中，默认就是false
            // }
            //从下至上，从右至左填写dp表
            for (int i = row; i >= 0; i--) { //指向s字符串
                for (int j = col - 1; j >= 0; j--) { //指向p字符串
                    if (j + 1 < col && p[j + 1] == '*') { //后面第1个字符是*的情况
                        if (i < row && (s[i] == p[j] || p[j] == '.')) {
                            dp[i][j] = dp[i + 1][j] ||  //当前字符相等或可替换，因后续有*，可再生当前相等字符
                                    dp[i + 1][j + 2] || //当前字符相等或可替换，则*可变为0个字符
                                    dp[i][j + 2]; //不替换当前字符
                        } else { //当前位置的字符，不相等的情况
                            dp[i][j] = dp[i][j + 2]; //*抹除p字符串当前位置的字符，填后续的结果
                        }
                    }
                    //不越界，并且当前字符相等，或可替换
                    if (i < row && (s[i] == p[j] || p[j] == '.')) {
                        //因为上诉代码可能已改动dp【i】【j】为true，这里或运算即可
                        dp[i][j] |= dp[i + 1][j + 1];
                    }
                    //上诉代码，都没能将其改为true，则就是false，java默认是false，可不写
                    //dp[i][j] |= false;
                }
            }
            return dp[0][0]; //左上角，起始位置就是答案
        }

        //dp空间压缩-因为观察经典dp表，会发现每个位置的计算，只依赖于下1行和右边第2列的值，可用变量代替
        private boolean process3(char[] s, char[] p) {
            int row = s.length;
            int col = p.length;
            boolean[] dp = new boolean[col + 1]; //开辟一行
            dp[col] = true; // indexOfS == s.length && indexOfP == p.length
            // dp[0 …… col - 1] = false; //java 中默认就是false

            for (int i = col - 2; i >= 0; i -= 2) { //最后一行的情况
                if (p[i + 1] == '*') { //也就是下面103行~108行的缩减，因为是最后一行，无论如何只会走108行的代码
                    dp[i] = dp[i + 2];
                }
            }

            //从下至上，从右至左填写dp表
            for (int i = row - 1; i >= 0; i--) { //指向s字符串
                boolean rightButton = dp[col]; //就是dp[i + 1][j + 1]的值
                boolean rightButtonTwo = false; //就是dp[i + 1][j + 2]的值
                dp[col] = false; //只有右下角是true，其余的是false
                for (int j = col - 1; j >= 0; j--) { //指向p字符串
                    boolean button = dp[j]; //dp[i+1][j]
                    dp[j] = false;
                    if (j + 1 < col && p[j + 1] == '*') { //后第一个字符是*
                        if (i < row && (s[i] == p[j] || p[j] == '.')) {
                            dp[j] = button || //dp[i+1][j]
                                    rightButtonTwo || //dp[i+1][j+2]
                                    dp[j + 2]; //dp[i][j + 2]
                        } else {
                            dp[j] = dp[j + 2]; //dp[i][j+2]
                        }
                    }
                    //下一个字符不是*的情况,但当前字符相等或可替换
                    if (i < row && (s[i] == p[j] || p[j] == '.')) {
                        dp[j] |= rightButton; //dp[i+1][j+1]
                    }
                    //上诉代码都没能使dp[j]=true，则最终就是false，java默认就是false，可不写
                    // dp[j] = false;
                    rightButtonTwo = rightButton; //更新dp[i+1][j+2]的值
                    rightButton = button;
                }
            }
            return dp[0];
        }
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        String s = "aa";
        String p = "a*";
        System.out.println(so.isMatch(s, p));
    }
}
