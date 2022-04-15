package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-15
 * Time: 11:06
 * Description:
 */


import java.util.Scanner;
import java.util.Stack;

public class Code39_CalcExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine(); // 读取换行符
            String[] arr = sc.nextLine().split(" ");
            System.out.println(calcExpression(arr));
        }
    }

    public static int calcExpression(String[] arr) {
        if (arr == null || (arr.length & 1) == 0) { // 数值+操作符是偶数个
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i].charAt(0);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int last = stack.pop();
                int pre = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(last + pre);
                        break;
                    case '-':
                        stack.push(pre - last);
                        break;
                    case '*':
                        stack.push(last * pre);
                        break;
                    case '/':
                        if (last == 0) {
                            stack.push(0);
                        } else {
                            stack.push(pre / last);
                        }
                        break;
                }
                continue;
            }
            stack.push(Integer.parseInt(arr[i]));
        }

        return stack.pop();
    }
}