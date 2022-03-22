package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-22
 * Time: 19:22
 * Description: LeetCode2211 统计道路碰撞的汽车的次数
 */
public class Code13_RoadCrash {
    class Solution {
        public int countCollisions(String directions) {
            if (directions == null) {
                return 0;
            }
            char[] ch = directions.toCharArray();
            boolean leftCrash = false; // 左边是否发生了碰撞
            int count = 0; //计算向右行驶的汽车数
            int res = 0;
            for (int i = 0; i < ch.length; i++) {
                if (ch[i] == 'L' && count != 0) { // 当前向左行驶，且左边有向右形式的
                    res += 2;
                    res += count - 1;
                    count = 0;
                    leftCrash = true;
                } else if (ch[i] == 'L' && leftCrash) { // 向左行驶，且左边有碰撞事故
                    res += 1;
                } else if (ch[i] == 'R') {
                    count++;
                } else if (ch[i] == 'S') {
                    res += count;
                    count = 0;
                    leftCrash = true;
                }
            }
            return res;
        }
    }
}
