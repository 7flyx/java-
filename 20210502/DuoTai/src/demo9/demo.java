package demo9;

public class demo {
    public static void main(String[] args) {
        //测试类

        PPAthlete a1 = new PPAthlete("马龙",30);
        a1.drill();
        a1.StudyEnglish();
        System.out.println(a1.getName() + ", " + a1.getAge());
        a1.eat();
        System.out.println("-------------");

        PPCoach c1 = new PPCoach("刘祥",20);
        System.out.println(c1.getName() + ", " + c1.getAge());
        c1.StudyEnglish();
        c1.Teach();
        c1.eat();
        System.out.println("-------------");

        BTAthlete a2 = new BTAthlete("乔丹",40);
        System.out.println(a2.getName() + ", " + a2.getAge());
        a2.drill();
        a2.eat();
        System.out.println("-------------");

        BTCoach c2 = new BTCoach("詹姆斯",38);
        System.out.println(c2.getName() + ", " + c2.getAge());
        c2.Teach();
        c2.eat();

    }
}
