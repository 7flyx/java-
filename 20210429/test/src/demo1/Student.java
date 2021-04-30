package demo1;

public class Student {
    private String name;
    private String age;
    private String ID;
    private String address;

    public Student() {
    }

    public Student(String ID, String name, String age, String address) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getID() {
        return ID;
    }
}
