package demo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"hello", "hello"};

        Trie root = new Trie();
        for (int i = 0; i < arr.length; i++) {
            root.addWord(arr[i]);
        }
        //root.delWord("hello");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (root.searchWord(s) != 0) {
            System.out.println("该字典树有这个" + s + " 单词");
        }

    }

    public static class Node {
        public int pass;
        public int end;
        public Node[] nexts; //下一个字母的地址

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }
        //增加
        public void addWord(String str) {
            char[] arr = str.toCharArray();
            root.pass++;
            Node node = root;
            for (char s : arr) {
                int index = s - 'a'; //以相应的ASCII码值差值，进行数组的下标存储
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                node.pass++; //经过这个结点，pass就加1
            }
            node.end++;
        }

        //删除
        public void delWord(String str) {
            //删除之前，应该查询一下这颗树有没有这个单词
            while (searchWord(str) != 0) {
                char[] arr = str.toCharArray();
                Node node = root;
                  node.pass--;
                for (int i = 0; i < str.length(); i++) {
                    int index = arr[i] - 'a';
                    node = node.nexts[index];
                    node.pass--;
                }
                node.end--;
            }
        }

        //查找
        public int searchWord(String str) {
            if (str == null) {
                return 0;
            }
            char[] arr = str.toCharArray();
            Node node = root;
            for (int i = 0; i < str.length(); i++) {
                int index = arr[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end; //返回最后那一个结点的end值即可
        }
    }
}
