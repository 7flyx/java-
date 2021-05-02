package demo9;

public class BTCoach extends Coach{

    //构造方法
    public BTCoach() {
    }

    public BTCoach(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void Teach() {
        System.out.println("教篮球");
    }
}
