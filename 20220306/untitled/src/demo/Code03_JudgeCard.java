package demo;

import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-10
 * Time: 10:57
 * Description:
 */
public class Code03_JudgeCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(judge(str));
    }

    public static String judge(String str) {
        if (str == null) {
            return "ERROR";
        }
        // 两手牌只可能是 单张 对子 顺子 炸弹 王炸 其中的一种情况
        if (str.contains("joker JOKER")) {
            return "joker JOKER";
        } else {
            // 牌数相等的情况
            String[] arr = str.split("-");
            String[] tmp1 = arr[0].split(" "); //左手牌
            String[] tmp2 = arr[1].split(" "); //右手牌
            int len1 = tmp1.length;
            int len2 = tmp2.length;
            //牌数不相等的情况
            // 牌数相等和王炸的情况都判断过，这里的牌数还不相等，其中有一副牌有4张牌的炸弹
            // 还有就是根本没有任何比较关系的情况
            if (len1 == 4 && len2 != 4) {
                return arr[0];
            } else if (len2 == 4 && len1 != 4) {
                return arr[1];
            } else if (len1 == len2) {
                // 判断第一张牌即可
                return count(tmp1[0]) > count(tmp2[0]) ?
                        arr[0] : arr[1]; //因为不会出现相等的情况
            }
        }
        return "ERROR";
    }

    private static int count(String str) {
        return "345678910JQKA2jokerJOKER".indexOf(str);
    }
}
