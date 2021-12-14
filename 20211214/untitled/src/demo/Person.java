package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:47
 * Description: 人类，子类有administrator，和OrdinaryUser
 */
public class Person {
    public int id; //学号或工号
    public String name;
    public IOption[] options;

    public Person(int id, String name, IOption[] options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }
}
