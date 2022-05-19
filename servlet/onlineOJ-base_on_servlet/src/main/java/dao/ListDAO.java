package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-19
 * Time: 15:50
 * Description: 添加题目合集
 */
public class AddListDAO {
    public static boolean addList(String listName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into classify_table values(null, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, listName);
            int ret = statement.executeUpdate();
            if (ret != 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
        return false;
    }
}
