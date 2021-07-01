package demo1;

import java.util.ArrayList;
import java.util.List;

public class demo3 {
    public static void main(String[] args) {
        /*
                1. 打印一个字符串的全部子序列
                2. 打印一个字符串的全部排列
         */

        String s = "aaa";
        //List<String> list = subString(s); //子序列
//        for (String str : list) {
//            System.out.println(str);
//        }

        List<String> res = printArrange(s); //全排列
        for (String str : res) {
            System.out.println(str);
        }

    }

    public static List<String> subString(String str) {
        if (str == null) {
            return null;
        }
        //打印一个字符串的全部子序列：  随机组合，前提是左右的顺序不能改变
        //判断每一个位置上的，两种情况，要与不要
        List<String> ans = new ArrayList<>();
        String path = "";
        process1(str.toCharArray(), 0, path, ans);
        return ans;
    }

    public static void process1(char[] chs, int index, String path, List<String> ans) {
        if (index == chs.length) {
            if (!path.equals("")) { //不是空字符串，就添加进去
                ans.add(path); //说明来到了最后的一个位置，只需要添加到ans中，返回即可
            }
            return;
        }
        process1(chs, index+1, path, ans); //不要当前位置的字符
        String yes = path + chs[index];
        process1(chs, index + 1, yes, ans); //要了当前位置的字符
    }

    public static List<String> printArrange(String str) {
        if (str == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        //process2(str.toCharArray(), 0, result);
        process3(str.toCharArray(), 0, result);
        return result;
    }

    //不过滤重复的元素
    public static void process2(char[] chs, int index, List<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
        }

        for (int i = index; i < chs.length; i++) {
            swap(chs, i, index); //考虑交换的情况
            process2(chs, i + 1, res);
            swap(chs, i, index); //需要还原现场，这样才能不损坏原数组，进入下一个支路
        }
    }

    //过滤重复的元素
    public static void process3(char[] chs, int index, List<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
        }

        //分支限界，比如 a已经来到过下标0的位置，下次a就不能再来到下标为0的位置了
        boolean[] visit = new boolean[26]; //假设是26个小写的字母
        for(int i = index; i < chs.length; i++) {
            if (!visit[chs[i] - 'a']) {
                visit[chs[i] - 'a'] = true;
                swap(chs, i , index);
                process3(chs, i + 1, res);
                swap(chs, i, index); //还原现场，不影响下次循环时，chs数组的情况
            }
        }
    }

    public static void swap(char[] chs, int index1, int index2) {
        char tmp = chs[index1];
        chs[index1] = chs[index2];
        chs[index2] = tmp;
    }
}
