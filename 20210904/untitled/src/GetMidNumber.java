import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by flyx
 * Description: 一个数据流中，可以随时返回这个数据流的中位数。
 * User: 听风
 * Date: 2021-09-04
 * Time: 9:21
 */
public class GetMidNumber {
    public static void main(String[] args) {

    }


    public static class MedianHolder {
        private final PriorityQueue<Integer> minPQ;
        private final PriorityQueue<Integer> maxPQ;

        public MedianHolder() {
            minPQ = new PriorityQueue<>();
            maxPQ = new PriorityQueue<>(new NumberCompare());
        }

        public static class NumberCompare implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; //降序
            }
        }

        private void modifyTwoHeapSize() {
            if (maxPQ.size() == minPQ.size() + 2) {
                minPQ.add(maxPQ.poll());
            } else if (maxPQ.size() + 2 == minPQ.size()) {
                maxPQ.add(minPQ.poll());
            }
        }

        /**
         *  大致思路：分为两个堆，大根堆和小根堆。即就是一个是升序，一个是降序
         *         在加入数据时，判断两个堆的大小，超过1了，就将多的堆中弹出一个，放入少的堆中
         *         此时就保证两个堆的大小，几乎是平分秋色
         */
        public void addNumber(int num) {
            if (maxPQ.isEmpty() || maxPQ.peek() > num) {
                maxPQ.add(num);
            } else {
                minPQ.add(num);
            }
            modifyTwoHeapSize();
        }

        public double getMidNumber() {
            int minHeapSize = minPQ.size();
            int maxHeapSize = maxPQ.size();
            if (minHeapSize == 0 && maxHeapSize == 0) {
                return 0.0;
            }

            int maxHeapNum = maxPQ.peek();
            int minHeapNum = minPQ.peek();
            if (((minHeapSize + maxHeapSize) & 1) == 0) {
                return (maxHeapNum + minHeapNum) / 2.0;
            }
            return minHeapSize > maxHeapSize? minHeapNum : maxHeapNum;
        }
    }
}
