package bean;

import common.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-06-02
 * Time: 9:33
 * Description: 写 一对一 数据库的。
 */
public class OneToOne {
    @Test
    public void oneToOne() {
        try (SqlSession session = DBUtil.openSession()) {
            List<Person> list = session.selectList("person.getOne");
            for (Person o : list) {
                System.out.println(o);
            }
        }
    }

    @Test
    public void test() {
        String s = removeDuplicateLetters("bcabc");
        System.out.println(s);
    }

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] ch = s.toCharArray();
        int[] count = new int[128];
        int N = s.length();
        for (int i = 0; i < N; i++) {
            count[ch[i]]++;
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && ch[i] <= ch[stack.peek()]
                    && count[ch[stack.peek()]] == 2) {

                count[ch[stack.pop()]]--;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            sb.append(ch[stack.pop()]);
        }
        return sb.reverse().toString();
    }


    @Test
    public void test2() {
        long num = dfs(50);
        System.out.println(num);
        System.out.println(Math.pow(10, 8));
        System.out.println(num > Math.pow(10, 8));
    }

    public long dfs(long n) {
        if (n == 1) {
            return 1;
        }
        return n * dfs(n - 1);
    }

    public int longestWPI(int[] hours) {
        if (hours == null) {
            return 0;
        }
        int N = hours.length;
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = (hours[i] > 8 ? 1 : -1) + preSum[i];
        }
        int L = 1;
        int R = 1;
        int ans = 0;
        while (L <= N) {
            while (R <= N && (preSum[R] - preSum[L - 1] >= 1 || )) {
                ans = Math.max(ans, R - L + 1);
                R++;
            }
//            { // 不满足，L++
            L++;
            R = Math.max(L, R);
        }
        return ans;
    }

    @Test
    public void test3() {
//        int[] arr = {6, 6, 9};
//        int[] arr = {6,6,6};
        int[] arr = {9,9,6,0,6,6,9};
        System.out.println(longestWPI(arr));
    }

}
