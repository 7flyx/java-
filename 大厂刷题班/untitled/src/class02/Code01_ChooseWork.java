package class02;

import java.awt.image.Kernel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-02-16
 * Time: 18:48
 * Description:
 * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {
    public static void main(String[] args) {
        int[] hard = {10, 20, 5, 7, 9};
        int[] money = {100, 50, 60, 8, 5};
        int[] ability = {10, 15, 20, 9, 3};
        int[] ans = chooseWork(generateJobArray(hard, money), ability);
        System.out.println(Arrays.toString(ans));
    }

    private static class Job {
        public int hard;
        public int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static class JobCompare implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) { // 难度低的在前面，难度一样的，按照钱多的在前面
            if (o1.hard == o2.hard) {
                return o2.money - o1.money;
            }
            return o1.hard - o2.hard;
        }
    }

    public static int[] chooseWork(Job[] arr, int[] ability) {
        if (arr == null || ability == null) {
            return new int[]{};
        }
        int m = ability.length;
        int[] ans = new int[m];
        Arrays.sort(arr, new JobCompare());
        TreeMap<Integer, Integer> map = new TreeMap<>(); // hard, money
        map.put(arr[0].hard, arr[0].money);
        int preJobHard = arr[0].hard;
        int preJobMoney = arr[0].money;
        for (int i = 1; i < arr.length; i++) { // 将所有的工作装入 TreeMap
            if (preJobHard != arr[i].hard && preJobMoney < arr[i].money) { // 进入表中的数据，必须是比前一个工作的工资更高
                map.put(arr[i].hard, arr[i].money);
                preJobHard = arr[i].hard;
                preJobMoney = arr[i].money;
            }
        }
        // 使用有序表中的接口 ：<=num 或者 >= num进行查询
        for (int i = 0 ; i  < m; i++) {
            Integer key = map.floorKey(ability[i]); // <= ability的最高工资
            ans[i] = key == null? 0 : map.get(key); // 值得注意的事floorKey有可能是返回的null值
        }
        return ans;
    }

    private static Job[] generateJobArray(int[] hard, int[] money) {
        if (hard == null || money == null || hard.length != money.length) {
            return new Job[]{};
        }
        Job[] ans = new Job[hard.length];
        for (int i = 0; i < hard.length; i++){
            ans[i] = new Job(hard[i], money[i]);
        }
        return ans;
    }
}
