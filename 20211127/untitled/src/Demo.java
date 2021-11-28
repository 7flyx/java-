import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-11-27
 * Time: 15:40
 * Description:
 */
public class Demo {
    public static void main(String[] args) throws SQLException{
//        testInsert();
        testDelete();
    }


    public static void testDelete() throws SQLException {
        // 1 数据库源
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java101?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("2001");

        //2 与数据库服务器建立连接
        Connection connection = dataSource.getConnection();

        //3 书写SQL语句
        String sql = "delete from student where id = ?";
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入删除的 id：");
        int id = sc.nextInt();

        //4 将SQL语句封装成PreparedStatement对象
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id); //将用户输入的参数，放入SQL语句中
        //执行SQL语句
        int ret = statement.executeUpdate();
        System.out.println("res: " + ret);
    }

    public static void testInsert() throws SQLException {
        //1 创建对象
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java101?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource)dataSource).setUser("root");
        ((MysqlDataSource)dataSource).setPassword("2001");

        //2 建立连接
        Connection connection = dataSource.getConnection();

        //3 书写SQL语句
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id：");
        int id = sc.nextInt();
        System.out.println("请输入姓名：");
        String name = sc.next();
        String sql = "insert into student values(?, ?)";

        //4 将SQL语句放入PreparedStatement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id); //第一参数表示的是上面SQL问号的位置
        statement.setString(2, name);

        int ret = statement.executeUpdate();
        System.out.println("res: " + ret);
    }
}
