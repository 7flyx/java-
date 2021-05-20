package demo3;

import java.util.ArrayList;
import java.util.Collections;

public class demo {
    public static void main(String[] args) {
        //模拟斗地主，实现洗牌，发牌，看牌

        //牌盒
        ArrayList<String> box = new ArrayList<>();
        String[] colors = {"♠","♥","♣","♦"};
        String[] num = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        //合成牌
        for (String i : colors) {
            for (String j : num) {
                box.add(i + j);
            }
        }
        box.add("大王");
        box.add("小王");

        //洗牌---collections中随机排序方法  shuffle
        Collections.shuffle(box);

        //发牌
        ArrayList<String> play1Array = new ArrayList<>();
        ArrayList<String> play2Array = new ArrayList<>();
        ArrayList<String> play3Array = new ArrayList<>();
        ArrayList<String> dp = new ArrayList<>();
        //System.out.println(box);
        for (int i = 0; i < box.size(); i++) {
            String poker = box.get(i);
            if (i >= box.size() - 3) {
                dp.add(poker);
            } else if(i % 3 == 0) {
                play1Array.add(poker);
            } else if(i % 3 == 1) {
                play2Array.add(poker);
            } else {
                play3Array.add(poker);
            }
        }

        //看牌
        lookPoker("陈冠希",play1Array);
        lookPoker("胡歌",play2Array);
        lookPoker("彭于晏",play3Array);
    }
    public static void lookPoker(String name, ArrayList<String> poker) {
        System.out.print(name + " 的牌是：");
        for (String p : poker) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
