package demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-15
 * Time: 19:31
 * Description:
 */
public class Code40_Maze {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] arr = new String[10];
            for (int i = 0; i < 10; i++) {
                arr[i] = sc.nextLine();
            }
            System.out.println(calcMinStepNum(arr));
        }
    }

    public static int calcMinStepNum(String[] arr) {
        if (arr == null) {
            return 0;
        }
        boolean[][] visit = new boolean[10][10];
        return dfs(arr, visit, 0, 1) - 1;
    }

    public static int dfs(String[] arr, boolean[][] visit, int i, int j) {
        if (i < 0 || i == 10 || j < 1 || j == 9 || arr[i].charAt(j) == '#' || visit[i][j]) {
            return Integer.MAX_VALUE;
        }
        if (i == 9 && j == 8) { // 到达出口
            return 1;
        }

        int res = Integer.MAX_VALUE;
        visit[i][j] = true;
        res = Math.min(res, dfs(arr, visit, i + 1, j));
        res = Math.min(res, dfs(arr, visit, i, j + 1));
        res = Math.min(res, dfs(arr, visit, i - 1, j));
        res = Math.min(res, dfs(arr, visit, i, j - 1));
        //visit[i][j] = false;
        return res == Integer.MAX_VALUE ? res : res + 1;
    }


    // bfs广度优先搜索
    public static class BFS {
        static int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        static class Node {
            int x, y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                char[][] maze = new char[10][10];
                int[][] map = new int[10][10];
                boolean[][] visited = new boolean[10][10];
                for (int i = 0; i < 10; i++) {
                    String str = sc.next();
                    for (int j = 0; j < 10; j++) {
                        maze[i][j] = str.charAt(j);
                        if (maze[i][j] == '#') {
                            visited[i][j] = true;
                        }
                    }
                }
                Queue<Node> queue = new LinkedList<>();
                queue.offer(new Node(0, 1)); // 用队列保存节点，bfs向四周扩散
                while (!queue.isEmpty()) {
                    Node cur = queue.poll(); // 弹出一个节点，跑四个方向
                    for (int i = 0; i < 4; i++) { // 遍历四个方向
                        Node next = new Node(cur.x + direction[i][0], cur.y + direction[i][1]);
                        if (next.x >= 0 && next.x < 10 && next.y >= 0 && next.y < 10
                                && !visited[next.x][next.y]) {
                            queue.offer(next);
                            map[next.x][next.y] = map[cur.x][cur.y] + 1;
                            visited[next.x][next.y] = true;
                        }
                    }
                }
                System.out.println(map[9][8]);
            }
        }
    }


    // dfs深度优先搜索

    class DFS {
        int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        public void dfs(int x, int y, char[][] maze, int[][] map) {
            for (int i = 0; i < 4; i++) {
                int xx = x + direction[i][0];
                int yy = y + direction[i][1];
                if (xx >= 0 && xx < 10 && yy >= 0 && yy < 10
                        && maze[xx][yy] == '.' && map[xx][yy] > map[x][y] + 1) { // 这里有一个最优结果的判断，也算是额外的条件，能够杀死一部分可能性
                    map[xx][yy] = map[x][y] + 1;
                    dfs(xx, yy, maze, map);
                }
            }
        }

        public void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                char[][] maze = new char[10][10]; // 迷宫
                int[][] map = new int[10][10]; // 统计最优的步数
                for (int i = 0; i < 10; i++) {
                    String str = sc.next();
                    for (int j = 0; j < 10; j++) {
                        maze[i][j] = str.charAt(j);
                        map[i][j] = Integer.MAX_VALUE;
                    }
                }
                map[0][1] = 0;
                dfs(0, 1, maze, map);
                System.out.println(map[9][8]);
            }
        }
    }
}
