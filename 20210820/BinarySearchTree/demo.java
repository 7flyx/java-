package BinarySearchTree;

/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-08-20
 * Time: 17:10
 */



public class demo {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.display();

        tree.remove2(3);
        tree.display();
        System.out.println(tree.contains(4));
    }
}
