package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-18
 * Time: 21:52
 * Description: 查找用户数据
 */
public class UserDAO {
    public User getUser(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from userinfo where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setPassSum(resultSet.getInt("passsum"));
                user.setManager(resultSet.getInt("manager") == 1);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null; // 没查询到，直接返回null
    }

    public void addUser(String username, String password, boolean manager) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into userinfo values(null, ?, ?, 0, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, manager? 1 : 0);
            int ret = statement.executeUpdate();
            if (ret != 0) {
                System.out.println("注册用户成功");
            } else {
                System.out.println("注册用户失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }
}
