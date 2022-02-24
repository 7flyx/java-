import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-24
 * Time: 20:18
 * Description:
 */
public class LeetCode212_WordSearch {
    static class Solution {
        private char[][] board;
        private boolean[][] visited;
        private boolean flag;
        private HashSet<String> set;
        private class Node {
            public int row;
            public int col;
            public Node(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            if (board == null || words == null) {
                return new ArrayList<>();
            }
            this.board = board;
            this.set = new HashSet<>();
            int row = board.length;
            int col = board[0].length;
            HashMap<Character, List<Node>> map = new HashMap<>();
            //将所有的字符的坐标放入表中
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    List<Node> list = null;
                    if (!map.containsKey(board[i][j])) {
                        list = new ArrayList<>();
                    } else {
                        list = map.get(board[i][j]);
                    }
                    list.add(new Node(i, j));
                    map.put(board[i][j], list);
                }
            }

            List<String> res = new ArrayList<>();
            //遍历words
            for (int i = 0; i < words.length; i++) {
                //如果不是空串，且包含第一个字符的话，搞一个深度搜索
                if (words[i].length() != 0 && map.containsKey(words[i].charAt(0))) {
                    for (Node node : map.get(words[i].charAt(0))) {
                        this.visited = new boolean[row][col];
                        this.flag = false;
                        dfs(words[i], 0, res, node.row, node.col);
                        if (this.flag) {
                            break;
                        }
                    }
                }
                if (words[i].length() == 1 && map.containsKey(words[i].charAt(0))) {
                    res.add(words[i]); //只有一个字符的情况
                }
            }
            return res;
        }


        //深度搜索-找单词
        private void dfs(String str, int index, List<String> res, int row, int col) {
            if (index == str.length() || this.set.contains(str)) {
                if(!this.set.contains(str)) {
                    res.add(str); //能够知道str的情况
                    this.flag = true;
                }
                this.set.add(str);
                return;
            }
            //没有当前字符的情况-直接返回
            if (str.charAt(index) != board[row][col]) {
                return;
            }

            visited[row][col] = true;
            //向下不越界的情况,
            if (row + 1 < board.length && !visited[row + 1][col]) {
                dfs(str, index + 1, res, row + 1, col);
            }
            //向上不越界的情况
            if (row - 1 >= 0 && !visited[row - 1][col]) {
                dfs(str, index + 1, res, row - 1, col);
            }

            //向左不越界的情况
            if (col - 1 >= 0 && !visited[row][col - 1]) {
                dfs(str, index + 1, res, row, col - 1);
            }

            //向右不越界的情况
            if (col + 1 < board[0].length && !visited[row][col + 1]) {
                dfs(str, index + 1, res, row, col + 1);
            }
        }
    }



    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public int end;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = 0;
        }

    }

    public static void fillWord(TrieNode head, String word) {
        head.pass++;
        char[] chs = word.toCharArray();
        int index = 0;
        TrieNode node = head;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public static String generatePath(LinkedList<Character> path) {
        char[] str = new char[path.size()];
        int index = 0;
        for (Character cha : path) {
            str[index++] = cha;
        }
        return String.valueOf(str);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        TrieNode head = new TrieNode(); // 前缀树最顶端的头
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (!set.contains(word)) {
                fillWord(head, word);
                set.add(word);
            }
        }
        // 答案
        List<String> ans = new ArrayList<>();
        // 沿途走过的字符，收集起来，存在path里
        LinkedList<Character> path = new LinkedList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // 枚举在board中的所有位置
                // 每一个位置出发的情况下，答案都收集
                process(board, row, col, path, head, ans);
            }
        }
        return ans;
    }

    // 从board[row][col]位置的字符出发，
    // 之前的路径上，走过的字符，记录在path里
    // cur还没有登上，有待检查能不能登上去的前缀树的节点
    // 如果找到words中的某个str，就记录在 res里
    // 返回值，从row,col 出发，一共找到了多少个str
    public static int process(
            char[][] board,
            int row, int col,
            LinkedList<Character> path,
            TrieNode cur,
            List<String> res) {
        char cha = board[row][col];
        if (cha == 0) { // 这个row col位置是之前走过的位置
            return 0;
        }
        // (row,col) 不是回头路   cha 有效

        int index = cha - 'a';
        // 如果没路，或者这条路上最终的字符串之前加入过结果里
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
            return 0;
        }
        // 没有走回头路且能登上去
        cur = cur.nexts[index];
        path.addLast(cha);// 当前位置的字符加到路径里去
        int fix = 0; // 从row和col位置出发，后续一共搞定了多少答案
        // 当我来到row col位置，如果决定不往后走了。是不是已经搞定了某个字符串了
        if (cur.end > 0) {
            res.add(generatePath(path));
            cur.end--;
            fix++;
        }
        // 往上、下、左、右，四个方向尝试
        board[row][col] = 0;
        if (row > 0) {
            fix += process(board, row - 1, col, path, cur, res);
        }
        if (row < board.length - 1) {
            fix += process(board, row + 1, col, path, cur, res);
        }
        if (col > 0) {
            fix += process(board, row, col - 1, path, cur, res);
        }
        if (col < board[0].length - 1) {
            fix += process(board, row, col + 1, path, cur, res);
        }
        board[row][col] = cha;
        path.pollLast();
        cur.pass -= fix;
        return fix;
    }








    public static void main(String[] args) {
        Solution so = new Solution();
        char[][] board = {{'a', 'a'}};
        String[] str = {"a"};
        so.findWords(board, str);
    }
}
