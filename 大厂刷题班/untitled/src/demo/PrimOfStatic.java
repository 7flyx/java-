package demo;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-10-18
 * Time: 19:20
 * Description: 普利姆算法，最小生成树，以点 解锁边，将边放入堆中，然后弹出。
 * 静态建图，也就四使用 链式前向星进行建图
 */
public class PrimOfStatic {
    static boolean[] vis = new boolean[5001];
    static int[] head = new int[5001];
    static int[] next = new int[200001 * 2];
    static int[] to = new int[200001 * 2];
    static int[] weight = new int[200001 * 2];
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            build(n, m);
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                in.nextToken();
                int w = (int) in.nval;
                addEdge(x, y, w);
                addEdge(y, x, w);
            }
            out.println(prim(n, m));
        }


        out.flush();
        out.close();
    }

    private static void addEdge(int x, int y, int w) {
        next[cnt] = head[x];
        to[cnt] = y;
        weight[cnt] = w;
        head[x] = cnt++;
    }

    private static void build(int n, int m) {
        cnt = 1;
        m <<= 1;
        Arrays.fill(head, 0, n + 1, 0);
        Arrays.fill(next, 0, m + 1, 0);
        Arrays.fill(to, 0, m + 1, 0);
        Arrays.fill(weight, 0, m + 1, 0);
        Arrays.fill(vis, 0, n + 1, false);
    }

    private static String prim(int n, int m) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        int ans = 0, nodeCnt = 0;
        heap.add(new int[]{1, 0}); // 从1号出发
        while (!heap.isEmpty()) {
            int[] pos = heap.poll();
            if (!vis[pos[0]]) {
                vis[pos[0]] = true;
                ans += pos[1];
                nodeCnt++;
                for (int ei = head[pos[0]]; ei > 0; ei = next[ei]) {
                    heap.add(new int[]{to[ei], weight[ei]});
                }
            }
        }
        System.out.println(nodeCnt);
        return nodeCnt == n ? ans + "" : "orz";
    }
}
