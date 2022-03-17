package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-10
 * Time: 18:40
 * Description: 用户账号的操作 比如注册账号之内的 都是要存储在数据库里面的。
 * 由于前端页面暂时还没写注册账号、删除账号等模块，这里就暂时不写这几个方法
 */
public class UserDao {
    // 根据userId进行查找对象
    public User getUserById(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from User where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
            return null; // 没有查询到结果的情况
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null; // 中途出现异常的情况
    }

    // 通过用户名进行查找
    public User getUserByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from User where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
            return null; //没有查询到的情况
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null; //中途出现异常的情况
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(1);
        System.out.println(user);
    }
}
