import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-27
 * Time: 8:46
 * Description: mysql数据库连接
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/message_wall?character=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2001";
    private static final DataSource dataSource = new MysqlDataSource();

    static {
        ((MysqlDataSource)dataSource).setURL(URL);   //设置URL连接
        ((MysqlDataSource)dataSource).setUser(USERNAME);    //用户名
        ((MysqlDataSource)dataSource).setPassword(PASSWORD); //密码
    }

    //获取连接
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //释放连接资源
    public void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
