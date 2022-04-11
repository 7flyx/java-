package demo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 10:20
 * Description: 4月11号 第二个代码题 mkdir
 */
public class Code32_MKDIR {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.parseInt(sc.nextLine());
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLine();
            }
            Test t = new Test();
            String[] strings = Arrays.copyOf(arr, arr.length);
            String test = t.test(arr);
            TrieTree trieTree = new TrieTree(strings);
            String path = trieTree.getPath();

            if (!test.equals(path)) {
                System.out.println("数组长度是 " + arr.length);
                System.out.println(Arrays.toString(strings));
                System.out.println();
//                System.out.println(test);
                FileOutputStream fileOutputStream = new FileOutputStream("E:\\Github_Coding\\java-\\20220306\\text.txt");
                fileOutputStream.write(test.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();
                System.out.println("==============================");
//                System.out.println(path);
                FileOutputStream fileOutputStream1 = new FileOutputStream("E:\\Github_Coding\\java-\\20220306\\my.txt");
                fileOutputStream1.write(path.getBytes(StandardCharsets.UTF_8));
                fileOutputStream1.close();
                break;
            }
        }
    }

    public static String getMkdir(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        TrieTree trieTree = new TrieTree(arr);
        return trieTree.getPath();
    }

    public static class Node {
        public HashMap<String, Integer> nextNode; // 存储下一节点在数组中的索引值
        public ArrayList<Node> path; // 存储下一节点

        public Node() {
            nextNode = new HashMap<>();
            path = new ArrayList<>();
        }
    }

    public static class TrieTree {
        private Node root; // 根节点

        public TrieTree(String[] arr) {
            root = new Node();
            for (String s : arr) {
                add(s);
            }
        }

        private void add(String s) {
            String[] tmp = s.split("/");
            Node node = root;
            for (int i = 1; i < tmp.length; i++) {
                if (!node.nextNode.containsKey(tmp[i])) {
                    int index = node.path.size();
                    node.nextNode.put(tmp[i], index);
                    node.path.add(new Node()); // 新建下级节点
                }
                node = node.path.get(node.nextNode.get(tmp[i]));
            }
        }

        // 返回前缀树上所有叶子节点的全路径
        public String getPath() {
            StringBuilder sb = new StringBuilder();
            List<String> res = new ArrayList<>();
            process(root, sb, res);
            res.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for (String s : res) {
                sb.append("mkdir -p /");
                sb.append(s.substring(0, s.length() - 1)).append("\n");
            }
            return sb.toString();
        }

        private void process(Node node, StringBuilder sb, List<String> res) {
            Set<Map.Entry<String, Integer>> entries = node.nextNode.entrySet();
            if (entries.size() == 0) {
                res.add(sb.toString());
                return;
            }
            for (Map.Entry<String, Integer> entry : entries) {
                String tmp = entry.getKey();
                int index = sb.length();
                sb.append(tmp + "/");
                process(node.path.get(entry.getValue()), sb, res);
                sb.delete(index, index + tmp.length() + 1);
            }
        }
    }


    public static class Test {
        public String test(String[] str) {
            // 排序以达到按照字典顺序
            Arrays.sort(str);
            for (int i = 1; i < str.length; i++) {
                // 排完序后最接近的字符串就会相邻
                // 若后者是在前者的路径之后延伸（即为/a/ab和/a/ab/abc的关系）
                // 就将前者变为""以便后续打印时判断，需要注意（/a/ab和/a/abc）这种情况
                if (str[i].contains(str[i - 1]) && str[i].charAt(str[i - 1].length()) == '/') {
                    str[i - 1] = "";
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                if (!"".equals(str[i])) {
                    sb.append("mkdir -p " + str[i]).append("\n");
                }
            }
            return sb.toString();
        }
    }

}
