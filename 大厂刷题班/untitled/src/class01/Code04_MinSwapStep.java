package class01;

import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:12
 * Description: 一个数组中只有两种字符'G'和'B'，可以让所有的G都放在左侧，所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧，但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
 */
public class Code04_MinSwapStep {
    public static void main(String[] args) {
        String str = "BGGBGG";
        System.out.println(swapStep(str));
    }

    public static int swapStep(String str) {
        if (str == null || str.length() <= 2) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        int pointG = 0;
        int pointB = 0;
        int ans1 = 0; // 先让chars[0]靠左
        int ans2 = 0; // 让char[0]的字符靠右
        while (pointB < N && pointG < N) {  // 先让G靠左，B靠右
            while (pointB < N && chars[pointB] != 'B') { // 往后找B字符
                pointB++;
            }
            while (pointG < N && chars[pointG] != 'G') { // 往后找G字符
                pointG++;
            }
            if (pointB < N && pointG < N) { // 两指针都还没越界的时候，进行统计
                ans1 += Math.abs(pointB - pointG);
                pointB++;
                pointG++;
            }
        }
        swap(chars, 0, N - 1); // 他两交换，后续就是让上一循环靠左的字符，去靠右是吧
        pointB = N - 1;
        pointG = N - 1;
        while (pointB >= 0 && pointG >= 0) {
            while (pointB >= 0 && chars[pointB] != 'B') {
                pointB--;
            }
            while (pointG >= 0 && chars[pointG] != 'G') {
                pointG--;
            }
            if (pointB >= 0 && pointG >= 0) {
                ans2 += Math.abs(pointB - pointG);
                pointB--;
                pointG--;
            }
        }
        return Math.min(ans1, ans2);
    }

    // 可以让G在左，或者在右。 左神写的
    public static int minSteps2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int step2 = 0;
        int gi = 0;
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') { // 当前的G，去左边   方案1
                step1 += i - (gi++);
            } else {// 当前的B，去左边   方案2
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    private static void swap(char[] chars, int pointB, int pointG) {
        char tmp = chars[pointB];
        chars[pointB] = chars[pointG];
        chars[pointG] = tmp;
    }
}
