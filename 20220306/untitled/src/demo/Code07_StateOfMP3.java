package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-16
 * Time: 10:47
 * Description: 3月16号 第二个代码题 mp3的光标位置
 */
public class Code07_StateOfMP3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String option = sc.next();
        System.out.println(getCurState(n, option.toCharArray()));
    }

    public static String getCurState(int n, char[] option) {
        if (n < 1 || option == null) {
            return n + "";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0; //统计option的操作，正数表示up，负数表示down
        for (int i = 0; i < option.length; i++) {
            if (option[i] == 'U') {
                count++;
            } else {
                count--;
            }
        }
        System.out.println(count);
        if (count == 0) {
            for (int i = 0; i < 4 && i < n; i++) {
                sb.append(i + 1 < 4 && i + 1 < n ? i + 1 + " " : i + 1 + "\n");
            }
            sb.append(1);
            return sb.toString();
        }

        boolean flag = count > 0; // true表示up，false表示down
        count = Math.abs(count) % n; // 去除重复的循环
        int index = 1; //光标
        if (flag) {
            index = n;
            index -= count - 1; // 往上走--以n为最下的其实（底部）
            for (int i = index; i < index + 4 && i <= n; i++) {
                sb.append(i + 1 < index + 4 && i + 1 <= n? i + " " : i + "\n");
            }
        } else {
            index += count; // 往下走---以1位最开始的位置（顶部）
            int start = Math.max(index - 3, 1);
            for (int i = start; i < start + 4 ; i++) {
                sb.append(i + 1 < start + 4 ? i + " " : i + "\n");
            }
        }
        sb.append(index);
        return sb.toString();
    }

}
