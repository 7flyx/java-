package demo;

import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-07-06
 * Time: 14:03
 * Description: LFU(least frequently used)：最不经常使用，内存淘汰算法。
 * 根据”使用频率“来定义，使用频率少的，就容易被替换掉。当使用频率相同时，谁最先进入结构，就先替换谁。
 * 也就是说会有两个定义：使用频率、时间点
 */


public class Code04_LFUCache {
    public HashMap<Integer, Node> map; // key对于 节点（key，value）
    public HashMap<Node, Bucket> allBucket; // 节点对应 桶
    public Bucket leftBucket; // 整个结构中，桶的头、尾节点
    private int size; // 当前最多存入size个Node节点
    public int cnt;
    public static void main(String[] args) {
        int[] key = {35732,4358,86206,17389,70456,37340,49958,43213,29722,76833,69993,3663,15540,42937,99326,15925,53843,54658,6414,28813,12348,9197,27262,45619,64633,2809,71237,57993,68542,10122,6167,5463,586,24046,51552,52583,45908,53023,46301,12593,85657};
        int[] val = {238625273,736216605,542977940,106542431,728248684,914172886,557231025,665566218,643361906,609762576,335146840,129117155,920785216,646596603,473595267,312768244,945065892,83575901,215669019,303418565,703675457,778840389,325733709,950092100,413368707,946600656,977189673,525521407,132688874,539463448,762167388,231459550,587516678,305463666,816168673,34139573,742226121,229366952,28203628,473528083,548422730};
        Code04_LFUCache lfu = new Code04_LFUCache(10);
        for (int i = 0; i < 500; i++) {
            lfu.put(key[i], val[i]);
        }
    }

    public Code04_LFUCache(int size) {
        this.size = size;
        map = new HashMap<>();
        allBucket = new HashMap<>();
        cnt = 0;
    }

    public static class Bucket {
        int usedFrequency; // 使用频率
        Bucket left, right; // left是频率小的桶，right是频率大的桶
        Code04_LFUCache.Node head, tail; // 当前桶里面，存储的数据的头节点、尾结点

        public Bucket(int usedFrequency, Code04_LFUCache.Node head, Code04_LFUCache.Node tail) {
            this.usedFrequency = usedFrequency;
            this.head = head;
            this.tail = tail;
        }

        public void destroy() {
                /*
                    当bucket这个哈希表，没有任何元素映射到这个桶时，这个桶就会被JVM进行回收。
                    C++系列的，就需要自己实现回收内存的函数
                */
            if (left == null && right == null) {
                return;
            }
            if (left != null) {
                left.right = right;
            }
            if (right != null) {
                right.left = left;
            }
            left = right = null;
        }

        // 在bucket实例对象的后面，新增桶
        public Bucket buildNewBucket(int usedFrequency, int direct) {
            if (right != null && right.usedFrequency == usedFrequency) {
                return right;
            }
            // 右边的桶的频率不等于usedFrequency，就需要新建
            Bucket b = new Bucket(usedFrequency, null, null);
            if (direct == 1) { // 在当前实例的右边插入新的桶
                b.right = right; // 将新桶插入到 this和right之间
                b.left = this;
                if (right != null) right.left = b;
                right = b;
            } else { // -1，在当前实例的左边，插入新的桶
                b.right = this;
                b.left = left;
                if (left != null) left.right = b;
                left = b;
            }
            return b;
        }

        public void add(Code04_LFUCache.Node node) {
            if (head == null) {
                head = tail = node;
            } else {
                tail.down = node;
                node.up = tail;
                tail = node;
                node.down = null;
            }
        }
    }


    // 双链表结构的节点，里面存储数据
    public class Node{
        int key;
        int val;
        Node up, down;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 桶：每一个桶代表着相同“使用频率”，Node节点中频率相同的，会存储在一个桶里面
    // 桶与桶之间，采用双链表结构进行存储


    public void put(int key, int value) {
        Node node = map.get(key);
        if (!map.containsKey(key)) { // 没有进来过，并且内存已经满了，就需要替换
            if (cnt == size) {
                node = new Node(key, value);
                Bucket cur = leftBucket;
                Node replace = cur.head; // 被替换的节点
                map.remove(replace.key, replace);
                allBucket.remove(replace);

                cnt--;
                map.put(key, node);
                if (cur.head == cur.tail) { // 这个桶只有1个节点
                    cur.head = cur.tail = node;
                    cur.usedFrequency = 1;
                    allBucket.put(node, leftBucket);
                } else if (cur.usedFrequency == 1) { // 这个桶的使用频率刚好是1，删除head，插入node
                    Node down = cur.head.down; // 新的头节点
                    cur.head.down = cur.head.up = null;
                    down.up = null;
                    cur.head = down;
                    // 插入新节点
                    cur.tail.down = node;
                    node.up = cur.tail;
                    cur.tail = node;
                    allBucket.put(node, leftBucket);
                } else { // 当前桶的频率不是1，需要再当前桶的前面，插入一个新的桶
                    Bucket newB = cur.buildNewBucket(1, -1);
                    // 删除旧桶里面的head，
                    Node down = cur.head.down; // 新的头节点
                    cur.head.down = cur.head.up = null;
                    down.up = null;
                    cur.head = down;
                    newB.head = newB.tail = node;
                    leftBucket = newB;
                    allBucket.put(node, newB);
                }
            } else { // 内存没有满
                node = new Node(key, value);
                map.put(key, node);
                if (leftBucket == null) {
                    leftBucket = new Bucket(1, node, node);
                } else {
                    if (leftBucket.usedFrequency == 1) { // 左边桶的使用频率是1，就加入node节点
                        leftBucket.add(node);
                    } else { // 新建桶，更新为新的leftBucket
                        Bucket newB = leftBucket.buildNewBucket(1, -1);
                        newB.head = newB.tail = node;
                        leftBucket = newB;
                    }
                }
                allBucket.put(node, leftBucket);
            }
            cnt++;
        } else { // 已经进来过，只是需要增加 使用频率
            node.val = value;
            move(node);
        }
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        // 更新使用频率，返回节点值
        move(node);
        return node.val;
    }

    // 增加使用频率
    private void move(Node node) {
        Bucket cur = allBucket.get(node);
        int curFrequency = cur.usedFrequency;
        if (node == cur.head && node == cur.tail) {
            cur.head = cur.tail = null; // 置空，回收当前桶
            // 建立新的桶，桶与桶之间要互相连接
            Bucket newB = cur.buildNewBucket(curFrequency + 1, 1); // 新桶
            allBucket.put(node, newB);
            // 将node节点插入新的桶里面
            node.down = node.up = null;
            newB.add(node);
            leftBucket = leftBucket == cur ? newB : leftBucket;
            cur.destroy(); // 这个桶没有节点了，就断开。
            return;
        }
        // 将node节点分离出来
        if (node == cur.head) { // node是头节点
            cur.head = cur.head.down;
            cur.head.up = null;
        } else if (node == cur.tail) { // node就是尾结点
            cur.tail = node.up;
            node.up.down = null;
            node.up = null;
        } else { // node在头、尾节点的中间
            node.up.down = node.down;
            node.down.up = node.up;
            node.down = node.up = null;
        }
        Bucket newB = cur.buildNewBucket(curFrequency + 1, 1); // 新桶
        allBucket.put(node, newB);
        // 将node节点插入新的桶里面
        node.down = node.up = null;
        newB.add(node);
    }
}