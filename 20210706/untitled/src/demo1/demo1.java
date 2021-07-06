package demo1;

public class demo1 {
    public static void main(String[] args) {
        //动态规划
        /*
                规定1和A对应、2和B对应、3和C对应...
                那么一个数字字符串比如“111”就可以转化为：
                "AAA"、 "KA"和"AK"
                给定一个只有数字字符组成的字符串str，返回有多少种转化结果
         */
        String s = "123123";
        System.out.println(num1(s));
        System.out.println(num2(s));
    }

    public static int num1(String str) {
        if (str == null) {
            return 0;
        }
        //暴力递归的形式
        return process1(str.toCharArray(), 0);
    }

    public static int process1(char[] chs, int index) {
        if (index == chs.length) {
            return 1;
        }
        if (chs[index] == '0') {
            return 0; //字符0的情况，直接排除
        }
        if (chs[index] == '1') {
            int res = process1(chs, index + 1);
            if (index + 1 < chs.length) {
                res += process1(chs, index + 2);
            }
            return res;
        }
        if (chs[index] == '2') {
            int res = process1(chs, index + 1);
            if (index + 1 < chs.length && chs[index + 1] >= '0' && chs[index + 1] <= '6') {
                res += process1(chs, index + 2);
            }
            return res;
        }
        return process1(chs, index + 1);
    }

    public static int num2(String str) {
        if (str == null) {
            return 0;
        }
        //动态规划----加入缓冲区---记忆化搜索
        //根据上面的暴力递归中分析，变量中一个index，所以在这里只需要一个一维数组即可
        char[] chs = str.toCharArray();
        int[] res = new int[str.length() + 1];
        for (int i = chs.length; i >= 0; i--) {
            if (i == str.length()) {
                res[i] = 1;
            } else if (chs[i] == '0') {
                res[i] = 0;
            } else if (chs[i] == '1') {
                res[i] = res[i + 1];
                if (i + 1 < str.length()) {
                    res[i] += res[i + 2];
                }
            } else if (chs[i] == '2') {
                res[i] = res[i + 1];
                if (i + 1 < str.length() && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
                    res[i] += res[i + 2];
                }
            } else {
                res[i] = res[i + 1];
            }
        }
        return res[0];
    }
}
