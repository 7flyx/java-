import java.util.LinkedList;

/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-27
 * Time: 20:29
 * Description: 两个栈，构成队列
 */
public class Code02_TwoStackComposeQueue {

    private LinkedList<Integer> qu1 = new LinkedList<>();
    private LinkedList<Integer> qu2 = new LinkedList<>();

    public Code02_TwoStackComposeQueue() {

    }

    public void push(int x) {
        if (!qu1.isEmpty()) {
            qu1.add(x);
        } else {
            qu2.add(x);
        }
    }

    public int pop() {
        if (empty()) {
            throw new RuntimeException("empty.");
        }

        if (!qu1.isEmpty()) {
            int size = qu1.size();
            for (int i = 1; i < size; i++) {
                qu2.add(qu1.poll());
            }
            return qu1.poll();
        }
        int size = qu2.size();
        for (int i = 1; i < size; i++) {
            qu1.add(qu2.poll());
        }
        return qu2.poll();
    }

    public int top() {
        if (empty()) {
            throw new RuntimeException("empty.");
        }
        if (!qu1.isEmpty()) {
            return qu1.peekLast();
        }
        return qu2.peekLast();
    }

    public boolean empty() {
        return qu1.isEmpty() && qu2.isEmpty();
    }
}



