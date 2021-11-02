package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-28
 * Time: 22:24
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(5);
        tree.insert(8);
        tree.insert(7);
        tree.insert(3);
        tree.insert(4);
        tree.insert(2);
        tree.printAll();

        tree.remove(6);
        tree.printAll();
    }
}
