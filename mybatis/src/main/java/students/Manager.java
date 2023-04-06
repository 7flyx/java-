package students;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-06
 * Time: 16:31
 * Description:
 */
public class Manager {
    private int managerID;
    private String managerName;

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID=" + managerID +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
