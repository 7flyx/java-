package demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-07-06
 * Time: 14:00
 * Description:
 */
public class Code03_LRUCache {
    /**
     内存替换算法：LRU。由哈希表+双链表实现
     */
    private Map<Integer, DoubleNode> map;
    private int size;
    private DoubleNode head, tail;

    public Code03_LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();
    }

    public int get(int key) { // get的时候，也是需要更新时间点的
        // if (map.size() == 0) return -1; // 没有数据
        DoubleNode node = map.get(key);
        if (node == null) return -1;
        int ans = node.val;
        put(key, node.val);
        return ans;
    }

    public void put(int key, int value) {
        DoubleNode node = map.get(key);
        if (node == null && map.size() != size) { // 没有插入过，并且内存区域还有空间
            node = new DoubleNode(key, value);
            map.put(key, node);
            if (head == null) {
                tail = head = node;
            } else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
            return;
        }
        // 已经插入过，或者内存满了，需要更换成新的头节点
        if(node == null) { // 还没插入过， 只是内存满了
            node = new DoubleNode(key, value);
            DoubleNode del = head;
            if (head == tail) {
                head = tail = node;
            } else {
                head = head.next;
                head.last = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            }
            map.remove(del.key); // 删除旧的节点
            map.put(key, node);
        } else { // 已经插入过，只是更新值
            node.val = value;
            // 1、node就是头结点
            if (node == head) {
                if(head == tail) { // 头尾节点都是一样的情况
                    head = tail = node;
                    return;
                }
                if (head.next != null) {
                    head.next.last = null;
                    head = head.next;
                }
                node.next = null;
                tail.next = node; // 连尾节点
                node.last = tail;
                tail = node;
            } else if (tail == node) { // 2、node就是尾结点 就不用管
                ;
            } else  { // 3、node在头、尾的中间
                node.last.next = node.next;
                node.next.last = node.last;
                node.last = tail;
                tail.next = node;
                node.next = null;
                tail = node;
            }
        }
    }

    // 双链表。应该设计为泛型，这里的题只是使用了int类型作为key和value而已
    private class DoubleNode {
        int key, val;
        DoubleNode last, next; // 前后指针
        public DoubleNode(int k, int v) {
            key = k;
            val = v;
        }
    }
}
