
import java.util.*;
import java.io.*;
/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-09
 * Time: 22:56
 * Description:
 */

public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = in.readLine().split(" ");
        int N = Integer.parseInt(nums[0]);
        int K = Integer.parseInt(nums[1]);

        System.out.println(topKNumber(in, N, K));
    }

    private static class Node {
        public String val; //值域
        public int number; //频率
        public Node(String val, int number) {
            this.val = val;
            this.number = number;
        }
    }

    /**
     * 以下的情况是大根堆组织的，不会忽略所有的重复值的情况，但是本质上这个堆需要将
     * 所有的数据都入堆进行调整时，时间复杂度过高。
     * @param in
     * @param N
     * @param k
     * @return
     * @throws IOException
     */
    public static String topKNumber2(BufferedReader in, int N, int k) throws IOException {
        if (k <= 0) {
            return "";
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.number == o2.number) {
                return o1.val.compareTo(o2.val);
            }
            return o2.number - o1.number;
        });

        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        for (Map.Entry<String, Integer> node : entry) {
            queue.add(new Node(node.getKey(), node.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        while ( !queue.isEmpty() && k-- != 0) {
            Node node = queue.poll();
            sb.append(node.val).append(" ").append(node.number).append("\n");
        }
        return sb.toString();
    }

    /**
     * 以下方法使用的是一个小根堆，则需要考虑重复值的情况，有可能同时有几个值相等，
     * 但是因为堆的大小原因，有一部分的值并没有放入堆里面，所以出现了漏掉的情况
     * @param in
     * @param N
     * @param k
     * @return
     * @throws IOException
     */
    public static String topKNumber(BufferedReader in, int N, int k) throws IOException {
        if (k <= 0) {
            return "";
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                return o2.getKey().compareTo(o1.getKey());
            }
            return o1.getValue() - o2.getValue();
        });

        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        for (Map.Entry<String, Integer> entries : entry) {
            if (minHeap.size() < k) {
                minHeap.add(entries);
            } else {
                if (minHeap.peek().getValue() < entries.getValue()) {
                    minHeap.poll();
                    minHeap.add(entries);
                }
            }
        }

        Node[] nodes =  new Node[k];
        while (k-- != 0) {
            Map.Entry<String, Integer> poll = minHeap.poll();
            nodes[k] = new Node(poll.getKey(), poll.getValue());
        }
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.number == o2.number) {
                return o1.val.compareTo(o2.val);
            }
            return o1.number - o2.number;
        });

        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.val).append(" ").append(node.number).append("\n");
        }
        return sb.toString();
    }
}
