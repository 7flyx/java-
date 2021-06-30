package demo;

import java.util.Arrays;

public class demo1 {
    public static void main(String[] args) {
        //汉诺塔问题

    }

    public static void hano(int n) {
        if (n > 0) {
            move(n, "left", "right", "mid");
        }
    }

    public static void move(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println(from + " -> " + to);
        } else {
            move(n - 1, from, other, to); //先将n-1层移动到中间，让路
            System.out.println(from + " -> " + to); //路让出来之后，将这一层移动到右边即可
            move(n - 1, other, to, from);  //  然后整体思想，将n-1层从中间移动到右边，实则底层还是会回到上面的两个问题
        }
    }


}
