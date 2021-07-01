package demo1;

public class demo1 {
    public static void main(String[] args) {
        //从左到右的尝试模型
        /*
                规定A和1对应，B和2对应，C和3对应.....
                那么一个数字字符串比如“111” 就可以转换为：
                "AAA"、“KA”或者“AK”
                给定一个只有数字字符串组成的str，返回有多少种转换结果
         */

        int sum = convertStr("111");
        System.out.println(sum);
    }

    public static int convertStr(String str) {
        if (str == null) {
            return 0;
        }

        char[] chs = str.toCharArray();
        return countStr(chs, 0);
    }

    public static int countStr(char[] chs, int index) {
        if (index == chs.length) {
            return 1;
        }
        if (chs[index] == '0') {
            return 0;
        }
        if (chs[index] == '1') { //是1的情况
            int count1 = countStr(chs, index + 1); //1，单独自己作为一个分支
            if (index + 1 < chs.length) {
                count1 += countStr(chs, index + 2); // 两个位置的数，作为一个分支
            }
            return count1; //进来了这里后，就没有其他的情况了，直接返回答案即可
        }
        if (chs[index] == '2') { //是2的情况
            int count2 = countStr(chs, index + 1);
            if (index + 1 < chs.length && (chs[index + 1] >= '0' && chs[index + 1] <= '6')) {
                count2 += countStr(chs, index + 2);
            }
            return count2;
        }

        //上面两个if都没有成立，说明当前这个数是在3~9之间的，就只能作为一种情况去尝试
        return countStr(chs, index + 1);
    }
}
