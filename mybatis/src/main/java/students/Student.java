package students;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-06
 * Time: 15:11
 * Description:
 */
public class Student {
    public int id;
    public String name;
    public Manager manager; // 他的经理

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
