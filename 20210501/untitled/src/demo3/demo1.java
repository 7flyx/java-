package demo3;

public class demo1 {
    public static void main(String[] args) {
        //继承中的访问特点，以及如何去运用这个关键字进行类方法的调用
        Zi z = new Zi(); //Fu类中无参构造方法
                        //  Zi类中无参构造方法--会同时去调用父类中的无参调用方法

        Zi z2 = new Zi(20); //Fu类中无参构造方法
                                //Zi类中带参构造方法 --还是会去调用父类中的无参调用方法

        //要想调用父类中的带参构造方法，只能使用super关键字，在子类中的构造方法里面使用
        Zi z3 = new Zi(20); //Fu类中带参构造方法
                                // Zi类中带参构造方法
    }
}
