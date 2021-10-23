/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-23
 * Time: 11:33
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        SizeBalancedTree sbt = new SizeBalancedTree();
        sbt.add(10);
        sbt.add(20);
        sbt.add(30);
        sbt.add(40);
        sbt.add(50);
        System.out.println(sbt.isEmpty());
        sbt.printInOrder();
        System.out.println(sbt.contains(60));
        System.out.println(sbt.contains(20));
        sbt.delete(20);
        sbt.printInOrder();
    }
}
