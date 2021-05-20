package demo1;

public class girlDemo {
    private String name;
    private int age;
    private int weight;
    private int height;  //身高
    private int leg_length;  //腿长
    private int waistline;  //腰围

    public girlDemo() {
    }

    public girlDemo(String name, int age, int weight, int height, int leg_length, int waistline) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.leg_length = leg_length;
        this.waistline = waistline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeg_length() {
        return leg_length;
    }

    public void setLeg_length(int leg_length) {
        this.leg_length = leg_length;
    }

    public int getWaistline() {
        return waistline;
    }

    public void setWaistline(int waistline) {
        this.waistline = waistline;
    }
}
