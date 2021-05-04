package demo6;

import demo2.AnimalOprator;

import java.util.Random;

public class demo {
    public static void main(String[] args) {

        AnimalOperation ao = new AnimalOperation();
        //使用了匿名内部类。在方法method中，需要传递的是以Jumpping为实现关系的子类，此处匿名创建了并传递
        ao.method(new Jumpping(){
            public void jump() {
                System.out.println("猫可以跳高了");
            }
        });

//        Random a = new Random();
//        System.out.println(a.nextInt(100) + 1);
    }
}
