package demo1;

public class PingPangPlayer extends Player implements SpeakEnglish{

    public PingPangPlayer() {
    }

    public PingPangPlayer(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("乒乓球运动员吃白菜");
    }
    public void speak() {
        System.out.println("说英语");
    }
    public void study() {
        System.out.println("训练乒乓球");
    }

}
