package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
