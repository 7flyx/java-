package class02;

import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-02-16
 * Time: 18:51
 * Description: 设计有setAll功能的哈希表，put、get、setAll方法，时间复杂度O(1)
 * 提示：加上时间点就能解决
 */
public class Code05_SetAll {
    public static void main(String[] args) {
        MyMap<Integer, Integer> map = new MyMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));

        map.setAll(5);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));

    }

    public static class MyMap<K, V> {
        // K 作为外表的键，V作为里面表的键，Integer是表示插入进来的时间点
        private final HashMap<K, Node<V, Integer>> hashMap;
        private int time;
        private Node<V, Integer> setAll; // 标志着是否调用过setAll（）

        public MyMap() {
            hashMap = new HashMap<>();
            setAll = new Node<>(null, 0);
            time = 1;
        }

        public void put(K key, V value) {
            Node<V, Integer> node = new Node<V, Integer>(value, time);
            hashMap.put(key, node);
            time++;
        }

        public void setAll(V value) {
            setAll = new Node<>(value, time);
            time++;
        }

        public V get(K key) {
            if (!hashMap.containsKey(key)) {
                return null;
            }
            // 判断取出的node的time值，如果当前this.time大于 node.time，
            Node<V, Integer> node = hashMap.get(key);
            if (setAll.time > node.time) { // 说明setAll后执行，会覆盖前面添加的内容
                return setAll.key;
            }
            return node.key;
        }
    }

    private static class Node<K, V> {
       public K key;
       public V time;

        public Node(K key, V time) {
            this.key = key;
            this.time = time;
        }
    }

}
