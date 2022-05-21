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
 * Time: 17:54
 * Description: 保存用户运行成功后的代码
 */
public class SaveCodeDAO {
    public static void saveCodeToDB(int problemID, String code, int userID) {
        if(isSavedCode(problemID, userID) != null) {
            deleteCode(problemID, userID); // 删除旧的数据
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into code_backups values (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemID);
            statement.setString(2, code);
            statement.setInt(3, userID);
            int ret = statement.executeUpdate();
            if (ret != 0) {
                System.out.println("代码备份成功");
            } else {
                System.out.println("代码备份失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    // 判断这个题的代码，这个用户是否已经保存过
    public static String isSavedCode(int problemID, int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select code from code_backups where problemID = ? and userID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemID);
            statement.setInt(2, userID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("code");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    // 删除指定的数据
    private static void deleteCode(int problemID, int userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from code_backups where problemID = ? and userID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemID);
            statement.setInt(2, userID);
            int ret = statement.executeUpdate();
            if (ret != 0) {
                System.out.println("代码删除成功");
            } else {
                System.out.println("代码删除失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

}
