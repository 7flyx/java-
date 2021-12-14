package demo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 17:20
 * Description: 连接数据库
 */
public class ConnectMysql {
    private static final DataSource dataSource;
    static {
        dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/mybooksys?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("2001");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = (Connection) dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
