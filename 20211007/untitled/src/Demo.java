import java.util.List;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-10-07
 * Time: 20:49
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        PlayCards cards = new PlayCards();
        List[] array = cards.deal();
        if (cards.No == 0) {
            System.out.println("地主是 玩家一");
        } else if (cards.No == 1) {
            System.out.println("地主是 玩家二");
        } else {
            System.out.println("地主是 玩家三");
        }
        System.out.println("玩家一的牌: " + array[0]);
        System.out.println("玩家二的牌: " + array[1]);
        System.out.println("玩家三的牌: " + array[2]);
        System.out.println("底牌: " + array[3]);

    }
}
