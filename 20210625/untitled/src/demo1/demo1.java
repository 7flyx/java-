package demo1;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class demo1 {
    public static void main(String[] args) {
        //并查集

        /*
            有若干个样本，a,b,c,d,.....，类型假设是V
            在并查集中，最开始认为每个样本都是一个单独的集合，用户可以调用一下的方法：
                boolean isSameSet(V a, V b),:  查询这两个集合是不是在一个集合中
                void union(V a, V b),: 把 a集合  和b集合  中所有的样本合并成一个集合
            这两个方法的代价越小越好

            注：  一般在调用union，会把 小集合挂在大集合下面， 且为了时间复杂度是O(1),在union时，会将所有的结点，挂在根结点的
            直接下级
         */


    }

    public static class Node<V> {
        V value;
        public Node (V value) {
            this.value = value;
        }
    }

    public static class User {
        public String a; //不同的字段
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static class unionSet<V> {
        public HashMap<V, Node<V>> nodes;  //V -> 结点，  记录对应关系，，  也就是记录数值对应的包装结点
        public HashMap<Node<V>, Node<V>> parents; //代替了Node类里面的  向上找的指针
        public HashMap<Node<V>, Integer> sizeMap; //记录代表点的大小， 代表点就是这个集合的头结点

        public unionSet(List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> node) {
            Stack<Node<V>> path = new Stack<>();
            while (node != parents.get(node)) {
                path.push(node);
                node = parents.get(node); //往上走
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), node);  //沿途路径上的所有结点，父节点都设置为当前node结点，使其扁平化
            }
            return node;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false; //如果根本没加入过其中一个点，就谈不上一不一样的事
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b)); //判断不是值，而是值对应的包装结点node
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return; //根本没有这个结点，直接返回
            }
            //拿到这两个结点集合的代表点
            Node<V> aHead = findFather(nodes.get(a)); //切记这里传递的不是值，而是值的包装结点
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) { //不相等， 说明不在一个集合中
                //计算两个集合的大小，来决定是谁挂在谁的下面
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize > bSetSize) { //b挂在a的下面
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize); //a代表点的总大小，就是两个集合的大小
                    sizeMap.remove(bHead); //删除b代表的大小，因为已经归属于a代表点了
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        public int getSetNum() {
            return sizeMap.size();
        }
    }

    //练习
    public static int findUser(List<User> users) {
        //给你一个集合，判断这里面有几个人，有些特征一样的，就是一个人
        if (users == null) {
            return 0;
        }

        unionSet<User> unionset = new unionSet<User>(users); //先将整个集合传过去
        //有几个字段，就建立几个哈希表
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();

        //遍历整个集合
        for (User user : users) {
            if (mapA.containsKey(user.a)) {
                unionset.union(user,mapA.get(user.a)); //合并
            } else {
                mapA.put(user.a, user);
            }

            if (mapB.containsKey(user.b)) {
                unionset.union(user,mapB.get(user.b)); //合并

            } else {
                mapB.put(user.b, user);
            }

            if (mapC.containsKey(user.c)) {
                unionset.union(user,mapC.get(user.c)); //合并

            } else {
                mapC.put(user.c, user);
            }
        }
        return unionset.getSetNum();
    }

}
