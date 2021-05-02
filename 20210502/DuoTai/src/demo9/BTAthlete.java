package demo9;

public class BTAthlete extends Athlete{

    //构造方法
    public BTAthlete() {
    }

    public BTAthlete(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void drill() {
        System.out.println("训练篮球");
    }
}
