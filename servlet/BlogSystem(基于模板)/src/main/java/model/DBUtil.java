package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-10
 * Time: 15:08
 * Description: model 指的是 操作数据库部分的代码
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/BlogSystem?characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2001";
    private static final DataSource dataSource = new MysqlDataSource(); // 数据库源

    static {
        // 对数据库进行设置用户名和密码操作
        ((MysqlDataSource)dataSource).setURL(URL);
        ((MysqlDataSource)dataSource).setUser(USERNAME);
        ((MysqlDataSource)dataSource).setPassword(PASSWORD);
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 关闭相应资源的链接
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
