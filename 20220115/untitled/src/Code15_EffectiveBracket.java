import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-04
 * Time: 10:58
 * Description:有效括号LeetCode20
 */
public class Code15_EffectiveBracket {
    public boolean isValid(String s) {
        if (s == null || s.length() < 1 || (s.length() & 1) == 1) {
            return false;
        }

        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {

            if (str[i] != '}' && str[i] != ')' && str[i] != ']') { //左括号
                stack.push(str[i]);
            } else { //右括号
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char ch = stack.pop();
                    if (!((ch == '(' && str[i] == ')') || (ch == '{' && str[i] == '}') || (ch == '[' && str[i] == ']'))) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
