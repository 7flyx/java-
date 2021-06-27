package demo1;

import java.util.HashMap;

public class Dijkstra {
    //最短路径---迪杰特斯拉算法
    //改进版---原先是在哈希表上做查找最短路径的操作，现在改进到在 小根堆上进行查找--需要自己改动一下堆的结构
    //自己实现heapify 和 insertHeapify方法
    //需要实现三功能---1. add 2. updata  3. ignore 集合于一个方法

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        public Node[] nodes; //在这个堆内部采用数组的形式
        public HashMap<Node, Integer> indexOfNode; //结点在数组中的下标，如果已经弹出了，下标值改为-1
        public HashMap<Node, Integer> distanceMap; //结点的目前最小距离
        public int size; //数组的大小

        public NodeHeap(int size) {
            nodes = new Node[size];
            indexOfNode = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isEnterd(Node node) {
            return indexOfNode.containsKey(node);
        }

        public boolean inHeap(Node node) {
            return isEnterd(node) && (indexOfNode.get(node) != -1);
        }

        public void addOrUpdataOrIgnore(Node node, int distance) {
            //如果下面两个if都没有执行，那就忽略
            if (inHeap(node)) { //在堆上，直接更新取距离的最小值，且做向上做insetHeapify
                indexOfNode.put(node, Math.min(distance, distanceMap.get(node)));
                insertHeapify(node, indexOfNode.get(node));
            }
            if (!isEnterd(node)) { //没有记录过
                nodes[size] = node;
                indexOfNode.put(node, size); //下标值
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord result = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);  //size本事先添加后 加了一个1的，所以需要先减去
            indexOfNode.put(nodes[size - 1], -1); //改为-1.代表已经弹出堆了
            distanceMap.remove(nodes[size - 1]); //移除这个结点的distance，即就是最短距离
            nodes[size - 1] = null;
            heapify(0, --size); //将数组最后的一个元素换到堆顶后，需要向下做Heapify下沉处理
            return result;
        }

        public void insertHeapify(Node node, int index) { //插入后，向上做比较判断处理
            //判断的是distance这个参数
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index, int size) { //先下 做下沉处理
            int left = (index << 1) + 1; //* 2 + 1，  拿到左孩子
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ?
                        left + 1 : left; //先判断右孩子是否存在且distance更小
                smallest = distanceMap.get(nodes[index]) > distanceMap.get(nodes[smallest]) ? index : smallest;
                if (index == smallest) {
                    break; //如果index的值没变，说明此事就是最小的了
                }
                swap(index, smallest);
                index = smallest;
                left = (index << 1) + 1; //继续拿到左孩子
            }
        }

        public void swap(int index1, int index2) {
            indexOfNode.put(nodes[index1], index2); //切记需要交换在哈希表中的数据
            indexOfNode.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public HashMap<Node, Integer> dijkstra(Node from, int size) {
        //返回from点，到各点的最短距离
        if (from == null) {
            return null;
        }

        HashMap<Node, Integer> result = new HashMap<>();
        NodeHeap nodeheap = new NodeHeap(size); //小根堆
        nodeheap.addOrUpdataOrIgnore(from, 0);
        while (!nodeheap.isEmpty()) {
            NodeRecord nodeRecord = nodeheap.pop();

            Node node = nodeRecord.node;
            int distance = nodeRecord.distance; //当前结点的最短距离
            result.put(node, distance);
            for (Edge edge : node.edges) {
                Node toNode = edge.to; //拿到终点
                nodeheap.addOrUpdataOrIgnore(toNode, distance + edge.weight);
            }
        }
        return result;
    }
}
