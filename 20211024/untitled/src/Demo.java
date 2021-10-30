/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-24
 * Time: 9:35
 * Description:
 */
class Algorithm <T extends Comparable<T>>{

    public T findMax(T[] array) {
        T tmp = array[0];
        for (int i = 1; i < array.length; i++) {
            if (tmp.compareTo(array[i]) > 1) {
                tmp = array[i];
            }
        }
        return tmp;
    }
}

class Algorithm2 {
    public static<T extends Comparable<T>>  T findMax(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }
}


public class Demo {
    public static void main(String[] args) {
        BinarySearchTree sbt = new BinarySearchTree();
        sbt.insertTree(10);
        sbt.insertTree(20);
        sbt.insertTree(5);
        sbt.insertTree(25);
        sbt.insertTree(15);
        sbt.insertTree(3);
        sbt.print(sbt.root);
        System.out.println();
        sbt.remove(10);
        sbt.print(sbt.root);
    }

}
