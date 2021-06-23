package demo;

import java.util.HashSet;

public class demo1 {
    public static void main(String[] args) {
        //贪心算法练习题
        // 1。给定一个字符串，只由‘X’和‘.’组成，‘X’处不能点灯，也不需要被照亮，
        // 如果灯可以照亮它本身的位置，还可以照亮前后一个单位的位置
        // 问，如果照亮该字符串所有的‘.’ ，则最少需要多少个灯


    }

    public static int getLightSum1(String str) {
        if (str == null) {
            return 0;
        }

        //暴力解法： 分为两个角度，这个位置点灯与不点灯
        //先转换为字符数组.  HashSet,存储下标值，表示这个下标上有灯
        return process1(str.toCharArray(), 0, new HashSet<Integer>());
    }

    public static int process1(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { //来到了最后一个位置上
            for (int i = 0; i < str.length; i++) {
                //循环遍历整个light，看是否整个表都有相应的灯
                if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            return lights.size();
        } else { //字符串还没遍历完，继续往下遍历，注意从两个角度出发，求min值
            //无论该点是'X'或是'.'，都尝试不点灯的情况
            int no = process1(str, index + 1, lights); //不点灯
            int yes = Integer.MAX_VALUE;

            //'.'的情况，尝试点灯
            if (str[index] == '.') {
                lights.add(index);
                yes = process1(str, index + 1, lights);
                lights.remove(index); //记得删除这个结点记录，因为会影响后面的递归调用
            }
            return Math.min(no, yes);
        }
    }

    public static int getLightSum2(String str) {
        //贪心算法
        if (str == null) {
            return 0;
        }

        char[] s = str.toCharArray();
        int lights = 0;
        int index = 0;
        while (index < s.length) {
            if (s[index] == 'X') {
                index++;
            } else {
                lights++;

                //这里的加2还是加3，取决于下一结点的状况
                if (index + 1 == s.length) {
                    break;
                } else {
                    if (s[index + 1] == '.') {
                        index += 3;
                    } else {
                        index += 2;
                    }
                }
            }
        }
        return lights;
    }
}
