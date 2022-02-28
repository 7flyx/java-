import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-28
 * Time: 17:34
 * Description: 剑指offer41 数据流中的中位数
 */
public class LeetCode41_DataStreamMidNumber {
    class MedianFinder {
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            this.maxHeap = new PriorityQueue<>((o1, o2)->o2 - o1); //左部分
            this.minHeap = new PriorityQueue<>(); //右部分
        }

        public void addNum(int num) {
            //默认放入大根堆
            //为了位置两个堆的所有数据，都是相对有序的，也就是大根堆的所有数都
            //小于小根堆的所有数
            if(minHeap.size() != maxHeap.size()) { //大根堆的多一些
                maxHeap.add(num);
                minHeap.add(maxHeap.poll());
            } else { //两个堆都相等的情况
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            int size = maxHeap.size() + minHeap.size();
            if((size & 1) == 1) { //奇数的时候
                return maxHeap.peek();
            } else if (size != 0 && (size & 1) == 0) { //偶数
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
            return -1.0; //为空的情况
        }
    }
}
