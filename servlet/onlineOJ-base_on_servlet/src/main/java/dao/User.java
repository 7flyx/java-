package dao;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-18
 * Time: 21:53
 * Description:
 */
public class User {
    private int userID;
    private String username;
    private String password;
    private int passSum;  // 总的通过题目数量
    private boolean manager; // 是不是管理员

    public int getPassSum() {
        return passSum;
    }

    public void setPassSum(int passSum) {
        this.passSum = passSum;
    }

    public boolean isManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passSum=" + passSum +
                ", manager=" + manager +
                '}';
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
