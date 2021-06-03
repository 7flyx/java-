package demo1;

public class Trie {

    public class Node1 {
        public int pass;
        public int end;
        Node1 nexts[];

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26]; //26个小写字母
        }
    }

    private Node1 root;

    public void insertNode1(String str) {
        if(str == null) {
            return;
        }
        char[] chs = str.toCharArray(); //转换为字符数组
        Node1 node = root; //指向头结点
        node.pass++;
        int path = 0;
        for (int i = 0; i < chs.length; i++) {
            path = chs[i] - 'a'; //得到差值，作为nexts数组的索引
            if(node.nexts[path] == null) {
                node.nexts[path] = new Node1();
            }
            node = node.nexts[path];
            node.pass++;
        }
        node.end++;
    }

    public int searchNode1(String str) {
        if(str == null) {
            return 0;
        }
        char[] chs = str.toCharArray();
        Node1 node = root;
        int path = 0;
        for (int i = 0; i < chs.length; i++) {
            path = chs[i] - 'a';
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }
        return node.end; //end是几，则就是加入了几个这样的字符串
    }

    public void deleteNode1(String str) {
        //删除字符串，需要注意的是如果删除后的结点pass为0，则就不需要再往下面走了，因为pass为0 ，则就是没有字符串通过这里
        //首先还需要查找这个树有没有这个字符串，有的字符串前缀一样，不判断就删错了
        //Java的虚拟机可以自动回收，（当没有任何变量指向这块内存空间时）
        // C/C++ 则需要自己进行手动释放
        while (searchNode1(str) != 0) {
            char[] chs = str.toCharArray();
            Node1 node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null; //jvm会自动处理后面的结点内存问题
                    return;
                }
                node = node.nexts[path]; //向下一结点移动
            }
            node.end--;
        }
    }
}
