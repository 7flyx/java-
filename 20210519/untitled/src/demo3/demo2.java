package demo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class demo2 {
    public static void main(String[] args) {
        //模拟实现斗地主，排序手中的牌
        //用HashMap集合存储每张牌对应的键。我们对键进行一个随机打乱再排序，通过这个键来找值（牌）
        HashMap<Integer, String> hm = new HashMap<>();
        ArrayList<Integer> array = new ArrayList<>();
        //花色牌与点数牌
        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] nums = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        //组合牌
        int index = 0; //记录牌数
        for (String i : colors) {
            for (String j : nums) {
                hm.put(index, i + j);
                array.add(index);
                index++;
            }
        }
        hm.put(index, "大王");
        array.add(index);
        index++;
        hm.put(index, "小王");
        array.add(index);
        //Collections的shuffle打乱
        Collections.shuffle(array);
        TreeSet<Integer> play1 = new TreeSet<>(); //TreeSet会自动排序
        TreeSet<Integer> play2 = new TreeSet<>();
        TreeSet<Integer> play3 = new TreeSet<>();
        TreeSet<Integer> dp = new TreeSet<>();
        
        for (int i = 0; i < array.size(); i++) {
            int x = array.get(i);
            if (i >= array.size() - 3) {
                dp.add(x);
            } else if (i % 3 == 0) {
                play1.add(x);
            } else if (i % 3 == 1) {
                play2.add(x);
            } else {
                play3.add(x);
            }
        }

        //看牌
        lookPoker("陈冠希", play1, hm);
        lookPoker("胡歌", play2, hm);
        lookPoker("彭于晏", play3, hm);
    }

    public static void lookPoker(String name, TreeSet<Integer> play, HashMap<Integer, String> hm) {
        System.out.print(name + " 的牌是：");
        for (Integer i : play) {
            System.out.print(hm.get(i) + " ");
        }
        System.out.println();
    }
}
