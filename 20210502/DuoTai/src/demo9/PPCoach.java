package demo9;

public class PPCoach extends Coach implements English{

    //构造方法
    public PPCoach() {
    }

    public PPCoach(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void Teach() {
        System.out.println("教乒乓球");
    }

    @Override
    public void StudyEnglish() {
        System.out.println("学习英语");
    }
}
