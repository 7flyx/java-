package demo1;

public class BasketballCoach extends Coach{

    public BasketballCoach() {
    }

    public BasketballCoach(String name, int age) {
        super(name, age);
    }

    //重写方法
    @Override
    public void eat() {
        System.out.println("篮球教练吃白菜");
    }
    public void teach() {
        System.out.println("篮球教练教传球与投篮");
    }
}
