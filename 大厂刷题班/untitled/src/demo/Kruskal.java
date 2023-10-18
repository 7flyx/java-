package demo;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-10-18
 * Time: 17:59
 * Description: 最小生成树 Kruskal，以边排序，+并查集判断是否连通
 */
public class Kruskal {
    static int[][] edges = new int[200001][3];
    static int[] father = new int[5001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int)in.nval;in.nextToken();
            int m = (int)in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken(); int x = (int)in.nval;
                in.nextToken(); int y = (int)in.nval;
                in.nextToken(); int w = (int)in.nval;
                edges[i][0] = x;
                edges[i][1] = y;
                edges[i][2] = w;
            }
            out.println(kruskal(n, m));
        }

        out.flush();
        out.close();
    }


    private static int find(int x) {
        if (father[x] == x) return x;
        int next = find(father[x]);
        father[x] = next;
        return next;
    }

    // 如果x、y不在一个集合，就合并，并返回true
    // 如果在一个集合，就返回false
    private static boolean union(int x, int y) {
        int fa1 = find(x), fa2 = find(y);
        if (fa1 != fa2) {
            father[fa1] = fa2;
            return true;
        }
        return false;
    }

    private static String kruskal(int n, int m ) {
        for (int i = 0; i <= n; i++) father[i] = i;
        Arrays.sort(edges, 0, m,(o1, o2) -> o1[2] - o2[2]);
        int ans = 0, edgeCnt = 0;
        for (int i = 0; i < m ; i++) {
            if (union(edges[i][0], edges[i][1])) {
                ans += edges[i][2];
                edgeCnt++;
            }
        }
        return edgeCnt == n - 1? ans + "" : "orz";
    }
}
