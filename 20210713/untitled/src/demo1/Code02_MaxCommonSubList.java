package demo1;

public class Code02_MaxCommonSubList {
    public static void main(String[] args) {
        // 查找两个字符串的最长公共子序列

        String s1 = "abdc1223";
        String s2 = "bdc123a";
        System.out.println(commonSubList(s1, s2));

    }

    public static int commonSubList(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        /*
                两个字符串，也就是两个参数。所有拿一个参数作为行，一个参数作为列，组成一个二维矩阵，
                进行尝试
         */
        int[][] dp = new int[str1.length()][str2.length()];
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();

        //初始化
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        for (int i = 1; i < dp.length; i++) { //第一列处理
            dp[i][0] = Math.max(dp[i - 1][0], chs1[i] == chs2[0] ? 1 : 0);
        }
        for (int j = 1; j < dp[0].length; j++) { //第一行处理
            dp[0][j] = Math.max(dp[0][j - 1], chs1[0] == chs2[j] ? 1 : 0);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //dp[i][j] = ?;
                //四种情况，当前（i,j）位置的元素不一样，（i-1，j）位置的元素一样，（i，j-1）位置的元素一样，（i，j）位置的元素一样
                //这里的一样和不一样，指的是两个字符串最后一个元素（i和j）
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chs1[i] == chs2[j]) { //也就是当前（i，j）位置的元素相等
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
//                else { //当前位置的元素不相等的情况，可以省略，因为在分析左边个上边的元素时，就相当于分析了左上角的元素
//                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
//                }
            }
        }
        return dp[chs1.length - 1][chs2.length - 1];
    }
}
