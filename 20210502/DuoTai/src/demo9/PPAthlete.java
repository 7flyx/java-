package demo9;

public class PPAthlete extends Athlete implements English{

    //构造方法
    public PPAthlete(){
    }
    public PPAthlete(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void drill() {
        System.out.println("训练乒乓球");
    }

    public void StudyEnglish() {
        System.out.println("学习英语");
    }
}
