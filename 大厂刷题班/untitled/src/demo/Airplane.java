package demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-08
 * Time: 13:25
 * Description:
 * N 架飞机准备降落到某个只有一条跑道的机场。其中第 i 架飞机在 Ti 时刻到达机场上空，到达时它的剩余油料还可以继续盘旋 Di 个单位时间，即它最早
 * 可以于 Ti 时刻开始降落，最晚可以于 Ti + Di 时刻开始降落。降落过程需要 Li个单位时间。
 * 一架飞机降落完毕时，另一架飞机可以立即在同一时刻开始降落，但是不能在前一架飞机完成降落前开始降落。请你判断 N 架飞机是否可以全部安全降落。
 * 【输入格式】
 * 输入包含多组数据。
 * 第一行包含一个整数 T，代表测试数据的组数。
 * 对于每组数据，第一行包含一个整数 N。
 * 以下 N 行，每行包含三个整数：Ti，Di 和 Li。
 * 【输出格式】
 * 对于每组数据，输出 YES 或者 NO，代表是否可以全部安全降落。
 * 【样例输入】
 * 2
 * 3
 * 0 100 10
 * 10 10 10
 * 0 2 20
 * 3
 * <p>
 * 0 10 20
 * 10 10 20
 * 20 10 20
 * 【样例输出】
 * YES
 * NO
 * 【样例说明】
 * 对于第一组数据，可以安排第 3 架飞机于 0 时刻开始降落，20 时刻完成降
 * 落。安排第 2 架飞机于 20 时刻开始降落，30 时刻完成降落。安排第 1 架飞机
 * 于 30 时刻开始降落，40 时刻完成降落。
 * 对于第二组数据，无论如何安排，都会有飞机不能及时降落。
 * 【评测用例规模与约定】
 * 对于 30% 的数据，N ≤ 2。
 * 对于 100% 的数据，1 ≤ T ≤ 10，1 ≤ N ≤ 10，0 ≤ Ti
 * , Di
 * , Li ≤ 105
 */
public class Airplane {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt(); //飞机的数量
            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                Node node = new Node();
                node.Ti = sc.nextInt(); // 到达时间
                node.Di = sc.nextInt(); // 盘旋时间
                node.Li = sc.nextInt(); // 降落时间
                nodes[i] = node;
            }
            Node[] nodes2 = Arrays.copyOf(nodes, N);
            // 重置标志位
            flag1 = true;
            flag2 = false;
            f1(nodes, N);
            f2(nodes2, N, 0);
            if (flag1 != flag2) {
                System.out.println("答案不相同：");
                System.out.println("贪心：" + (flag1 ? "YES" : "NO"));
                System.out.println("暴力：" + (flag2 ? "YES" : "NO"));
            }
        }
    }

    private static boolean flag1 = true;

    // 贪心
    public static void f1(Node[] nodes, int N) {
        Arrays.sort(nodes, new Compare());

        int startTime = 0;
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            startTime = Math.max(startTime, nodes[i].Ti);
            // 判断startTime是否大于当前第i架飞机的盘旋时间，如果大于，这表示剩余油量不够用
            if (startTime > nodes[i].Ti + nodes[i].Di) {
                flag1 = false;
                break;
            }
            startTime += nodes[i].Li; // 这一个飞机的降落完成的时间点
        }
//        System.out.println(flag ? "YES" : "NO");
    }

    // 暴力解
    private static boolean flag2 = false;

    public static boolean f2(Node[] nodes, int N, int index) {
        if (index == N) {
            // 判断
            int time = 0;
            for (int i = 0; i < N; i++) {
                time = Math.max(time, nodes[i].Ti);
                if (time > nodes[i].Ti + nodes[i].Di) { // 开始降落的时间大于 盘旋时间, 就能完成降落
                    return false;
                }
                time += nodes[i].Li; // 开始降落，确定好降落完成时间
            }
            flag2 = true;
            System.out.println(Arrays.toString(nodes));
            return true;
        }
        if (f2(nodes, N, index + 1)) {
            // 不交换
            return true;
        }
        for (int i = index + 1; i < N; i++) {
            swap(nodes, index, i);
            if (f2(nodes, N, index + 1)) {
                return true;
            }
            swap(nodes, index, i);
        }
        return false;
    }

    private static void swap(Node[] nodes, int index, int i) {
        Node tmp = nodes[index];
        nodes[index] = nodes[i];
        nodes[i] = tmp;
    }

    private static class Compare implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            // 从到达时间就开始盘旋，以盘旋的时间进行升序排序
//            return (o1.Ti + o1.Di) - (o2.Ti + o2.Di);
            return (o1.Ti +o1.Di + o1.Li) - (o2.Ti + o2.Di + o2.Li);
        }
    }

    private static class Node {
        public int Ti; // 到达时间
        public int Di; // 盘旋时间
        public int Li; // 降落时间

        public Node() {

        }

        public Node(int ti, int di, int li) {
            Ti = ti;
            Di = di;
            Li = li;
        }

        @Override
        public String toString() {
            String s = "Ti: " + Ti + " Di: " + Di + ", Li: " + Li + "# ";
            return s;
        }
    }

    public static Node[] generateArray(int range, int num) {
        Node[] nodes = new Node[num];
        for (int i = 0; i < num; i++) {
            Node node = new Node();
            node.Ti = (int) Math.abs(Math.random() * range - Math.random() * range);
            node.Di = (int) Math.abs(Math.random() * range - Math.random() * range);
            node.Li = (int) Math.abs(Math.random() * range - Math.random() * range);
            nodes[i] = node;
        }
        return nodes;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 200;
        System.out.println("test begin.");
        while (T-- > 0) {
            int N = 3; //飞机的数量
            Node[] nodes = generateArray(200, N);
            Node[] nodes2 = Arrays.copyOf(nodes, N);
            // 重置标志位
            flag1 = true;
            flag2 = false;
            f1(nodes, N);
            f2(nodes2, N, 0);
            if (flag1 != flag2) {
                System.out.println("答案不相同：");
                System.out.println("贪心：" + (flag1 ? "YES" : "NO"));
                System.out.println("贪心的解法：" + Arrays.toString(nodes));
                System.out.println("暴力：" + (flag2 ? "YES" : "NO"));
                break;
            }
        }
        System.out.println("test finished.");
    }

}
