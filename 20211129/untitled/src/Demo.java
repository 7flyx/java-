import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-29
 * Time: 15:03
 * Description:
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        //testInsert();
        testSelect();
    }

    public static void testInsert() throws SQLException {
        //1 获取数据库来源
        DataSource dataSource = new MysqlDataSource();
        //2 设置ip地址 端口号等等
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java101?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("2001");
        //3 连接数据库服务器
        Connection connection = dataSource.getConnection();
        //4 书写SQL语句
        String sql = "insert into student values(?,?)";
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id：");
        int id = sc.nextInt();
        System.out.println("请输入name:");
        String name = sc.next();

        //5 将SQL语句包装为PreparedStatement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setString(2,name);

        int ret = statement.executeUpdate(); //增加 删除 修改都是这个方法
        System.out.println("ret:" + ret);

        statement.close();
        connection.close();
    }


    public static void testSelect() throws SQLException {
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java101?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("2001");

        Connection connection = dataSource.getConnection();

        String sql = "select id,age,name from student";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            //这里的列号，是相对于上面select语句而言的。而不是数据库的那张表
            int anInt = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println("id: " + anInt + " name: " + name);
        }
        statement.close();
        connection.close();
    }
}
