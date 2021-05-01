package demo4;

public class demo1 {
    public static void main(String[] args) {
        //继承中，方法重写---跟普通的方法重载是一个思想，在子类中有跟父类中相同的功能，但又
        //有一些差别，此时就需要进行方法的重写

        // 值得注意的是方法重写时的访问权限，重写方法的权限要高与已经有了的方法权限
        //1. 访问权限的大小关系：  public > 默认 > private
        //2. 私有的方法，不能进行重写。（父类的私有成员，子类是不能继承的）
        Zi z = new Zi();
        z.menthod();
        z.show();
    }
}
