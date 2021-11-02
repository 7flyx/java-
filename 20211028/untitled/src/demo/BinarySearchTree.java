package demo;


/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-28
 * Time: 21:29
 * Description: 模拟实现搜索二叉树
 *
 */
public class BinarySearchTree<T extends Comparable<T>>{
    private Node<T> root;
    private int size;
    private static class Node<T> {
        public T key;
        public Node<T> left, right;
        public Node(T key) {
            this.key = key;
        }
    }

    public BinarySearchTree() {
    }

    public void insert(T key) {
        if (root == null) {
            root = new Node<>(key);
        } else {
            Node<T> cur = root;
            Node<T> prev = null;
            Node<T> node = new Node<>(key);
            while (true) {
                if (cur == null) {
                    if (prev.key.compareTo(key) > 0) {
                        prev.left = node;
                    } else {
                        prev.right = node;
                    }
                    break;
                } else if (root.key.compareTo(key) > 0) {
                    prev = cur;
                    cur = cur.left;
                } else {
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        this.size++;
    }

    public void remove(T key) {
        if (root == null) {
            return;
        }

        root = remove(root, key);
        this.size--;
    }

    private Node<T> remove(Node<T> node, T key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else { //左右两个孩子都有的情况
                Node<T> des = node.right;
                Node<T> pre = null;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                }

                if (pre != null) {
                    pre.left = des.right;
                    des.right = node.right;
                }

                des.left = node.left;
                node = des;
            }
        }
        return node;
    }

    public void printAll() {
        if (root == null) {
            return;
        }
        printAll(root);
        System.out.println();
    }

    private void printAll(Node<T> node) {
        if (node == null) return;
        printAll(node.left);
        System.out.print(node.key + " ");
        printAll(node.right);
    }
}
