package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-19
 * Time: 15:50
 * Description: 添加题目合集
 */


public class ListDAO {
    public static class ListName { // 返回json的格式
        public int number;
        public String list;
    }

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

    public static int checkList(String listName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet =  null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select classify from classify_table where classify_name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, listName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) { // 能够查询到的情况下，直接返回true
                return resultSet.getInt("classify");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
        return -1;
    }

    public static List<ListName> getListAll() {
        List<ListName> ans = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from classify_table";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ListName list = new ListName();
                list.number = resultSet.getInt("classify");
                list.list = resultSet.getString("classify_name");
                ans.add(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return ans;
    }
}
