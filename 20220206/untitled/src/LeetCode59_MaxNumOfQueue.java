import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-21
 * Time: 17:10
 * Description:剑指offer59 队列的最大值
 */
public class LeetCode59_MaxNumOfQueue {
    class MaxQueue {
        private LinkedList<Integer> queue;
        private LinkedList<Integer> help;

        public MaxQueue() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public int max_value() {
            if (help.size() != 0) {
                return help.peek();
            }
            return -1; //为空的情况
        }

        public void push_back(int value) {
            queue.add(value);
            while (!help.isEmpty() && help.peek() < value) {
                help.poll();
            }
            help.add(value);
        }

        public int pop_front() {
            if (queue.size() != 0) {
                int tmp = queue.poll();
                if (help.peek().equals(tmp)) {
                    help.poll();
                }
                return tmp;
            }
            return -1; //为空的情况
        }
    }



}
