import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-04
 * Time: 14:23
 * Description:用栈实现队列LeetCode232
 */
public class Code17_StackToQueue {
    class MyQueue {
        private Stack<Integer> stack;
        private Stack<Integer> help;

        public MyQueue() {
            stack = new Stack<>();
            help = new Stack<>();
        }

        public void push(int x) {
            help.push(x);
            if (stack.isEmpty()) {
                while (!help.isEmpty()) {
                    stack.push(help.pop());
                }
            }
        }

        public int pop() {
            if (help.isEmpty() && stack.isEmpty()) {
                return -1;
            }
            if(stack.isEmpty()) {
                while (!help.isEmpty()) {
                    stack.push(help.pop());
                }
            }
            return stack.pop();
        }

        public int peek() {
            if (help.isEmpty() && stack.isEmpty()) {
                return -1;
            }
            if(stack.isEmpty()) {
                while (!help.isEmpty()) {
                    stack.push(help.pop());
                }
            }
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty() && help.isEmpty();
        }
    }
}
