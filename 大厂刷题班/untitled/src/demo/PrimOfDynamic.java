package demo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-10-18
 * Time: 19:52
 * Description: 普利姆算法，最小生成树，以点去解锁边，用邻接表进行建图
 * https://www.luogu.com.cn/problem/P3366
 */
public class PrimOfDynamic {
    static boolean[] vis = new boolean[5001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            ArrayList<ArrayList<int[]>> g = new ArrayList<>();
            for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                in.nextToken();
                int w = (int) in.nval;
                g.get(x).add(new int[]{y, w});
                g.get(y).add(new int[]{x, w});
            }
            out.println(prim(g, n, m));
        }
        out.flush();
        out.close();
    }

    private static String prim(ArrayList<ArrayList<int[]>> g, int n, int m) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        Arrays.fill(vis, 0, n + 1, false);
        heap.add(new int[]{1, 0});
        int ans = 0, nodeCnt = 0;
        while (!heap.isEmpty()) {
            int[] pos = heap.poll();
            if (!vis[pos[0]]) {
                ans += pos[1];
                nodeCnt++;
                vis[pos[0]] = true;
                for (int[] next : g.get(pos[0])) {
                    heap.add(next);
                }
            }
        }
        return nodeCnt == n ? ans + "" : "orz";
    }
}
