import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-04
 * Time: 10:23
 * Description:用栈构建数组LeetCode1441
 */
public class Code13_StackToArray {
    public List<String> buildArray(int[] target, int n) {
        if (target == null || target.length == 0 || n < 1) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int index = 0; //指向target数组
        for (int i = 1; i <= n && index < target.length; i++) {
            if (target[index] == i) {
                res.add("Push");
                index++;
            } else {
                res.add("Push");
                res.add("Pop");
            }

        }
        return res;
    }
}
