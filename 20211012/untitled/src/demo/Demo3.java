package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-12
 * Time: 15:48
 * Description:
 */

class Money implements Cloneable {
    public int number;
    public Money(int number) {
        this.number = number;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    public String name = "Tom";
    public Money money = new Money(100);
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person) super.clone();
        clone.money = (Money) this.money.clone();
        return clone;
    }
}

public class Demo3 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        Person person1 = (Person)person.clone(); //调用person对象的克隆方法

        System.out.println(person.name + " " + person.money.number);
        System.out.println("===========");
        person1.money.number = 20;
        System.out.println(person1.name + " " + person1.money.number);
    }
}
