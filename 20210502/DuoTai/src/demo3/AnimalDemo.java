package demo3;

public class AnimalDemo {
    public static void main(String[] args) {
        //多态中的转型---（强制类型转换）
        // 1. 向上转型 （从子到父）   2. 向下转型（从父到子）

        Animal a = new Cat();  //向上转型。  从子类转到父类
        a.eat(); //编译看父类，执行看子类.  重写的原因
        //a.play(); //特有方法，父类并没有这个方法,报错

        //向下转型
        Cat c = (Cat)a; //从父类转换为子类
        c.play();  //特别需要注意的是：强制类型转换时，等号左边接收的类型必须与右边的原类型一直，比如 a原先是Dog，现在转换为Cat为出错
    }
}
