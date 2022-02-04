import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-04
 * Time: 14:15
 * Description:用队列实现栈LeetCode225
 */
public class Code16_QueueToStack {
    class MyStack {
        private Queue<Integer> list1;
        private Queue<Integer> list2;

        public MyStack() {
            list1 = new LinkedList<>();
            list2 = new LinkedList<>();
        }

        public void push(int x) {
            if (list1.size() != 0) {
                list1.add(x);
            } else {
                list2.add(x);
            }
        }

        public int pop() {
            if (list1.size() == 0 && list2.size() == 0) {
                return -1;
            }
            if (list1.size() != 0) {
                while (list1.size() > 1) {
                    list2.add(list1.poll());
                }
                return list1.poll();
            }

            while (list2.size() > 1) {
                list1.add(list2.poll());
            }
            return list2.poll();
        }

        public int top() {
            if (list1.size() == 0 && list2.size() == 0) {
                return -1;
            }
            if (list1.size() != 0) {
                while (list1.size() > 1) {
                    list2.add(list1.poll());
                }
                int res = list1.poll();
                list2.add(res);
                return res;
            }

            while (list2.size() > 1) {
                list1.add(list2.poll());
            }
            int res = list2.poll();
            list1.add(res);
            return res;
        }

        public boolean empty() {
            return list1.size() == 0 && list2.size() == 0;
        }
    }
}
