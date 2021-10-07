import javax.smartcardio.Card;
import java.util.*;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-07
 * Time: 20:27
 * Description: 模拟斗地主
 */
public class PlayCards {
    private final String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private final String[] color = {"♥", "♠", "♣", "♦"};
    private List<String> box;
    public int No; //地主标号

    public PlayCards() {
        box = new ArrayList<>();
        for (int i = 0; i < this.numbers.length; i++) {
            for (int j = 0; j < this.color.length; j++) {
                this.box.add( this.color[j] + this.numbers[i]);
            }
        }
        this.box.add("Big King");
        this.box.add("small King");
    }

    private static class CardCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String str1 = o1.substring(1);
            String str2 = o2.substring(1);
            if (str1.equals("10")) {
                if (str2.equals("J") || str2.equals("Q") || str2.equals("K") || str2.equals("A")) {
                    return -1;
                }
                return 1;
            } else if (str2.equals("10")) {
                if (str1.equals("J") || str1.equals("Q") || str1.equals("K") || str1.equals("A")) {
                    return 1;
                }
                return -1;
            }
            return str1.compareTo(str2); //字典序比较
        }
    }

    public List[] deal() {
        List<String> play1 = new ArrayList<>();
        List<String> play2 = new ArrayList<>();
        List<String> play3 = new ArrayList<>();
        List<String> bottom = new ArrayList<>();

        washCard();
        int landlord = (int)(Math.random() * 54);
        System.out.println("地主牌是: " + this.box.get(landlord));
        for (int i = 0; i < 51; i++) {
            if(this.box.get(i).equals(this.box.get(landlord))) {
                this.No = i % 3;
            }
            if (i % 3 == 0) {
                play1.add(this.box.get(i));
            } else if (i % 3 == 1) {
                play2.add(this.box.get(i));
            } else {
                play3.add(this.box.get(i));
            }
        }
        bottom.add(this.box.get(51));
        bottom.add(this.box.get(52));
        bottom.add(this.box.get(53));
        play1.sort(new CardCompare());
        play2.sort(new CardCompare());
        play3.sort(new CardCompare());
        bottom.sort(new CardCompare());
        return new List[] {play1, play2, play3, bottom};
    }

    private void washCard() {
        int left = 0;
        int right = 0;
        String tmp = null;
        for (int i = 0; i < 54; i++) {
            left = (int)(Math.random() * 54);
            right = (int)(Math.random() * 54);
            //交换
            tmp = this.box.get(left);
            this.box.set(left, this.box.get(right));
            this.box.set(right, tmp);
        }
    }
}
