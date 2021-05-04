package demo1;

public class PingPangCoach extends Coach implements SpeakEnglish{

    public PingPangCoach() {
    }

    public PingPangCoach(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("乒乓球教练吃大白菜");
    }
    public void teach() {
        System.out.println("【乒乓球教练教发球与接球");
    }
    public void speak() {
        System.out.println("乒乓球教练要是英语");
    }
}
