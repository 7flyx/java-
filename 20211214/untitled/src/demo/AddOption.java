package demo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:56
 * Description:
 */
public class AddOption implements IOption {
    public void work() throws SQLException {
        Connection connection = ConnectMysql.getConnection(); //获取数据库的连接
        PreparedStatement statement = null;
        String sql = "insert into book values(?,?,?,?,?)";
        Book book = getBook();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getIsBorrow());

            int ret = statement.executeUpdate(); //执行SQL语句
            System.out.println(ret == 1? "添加成功" : "添加失败"); //影响到的行数
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally { //假设 抛出了异常，需要手动关闭Connection的连接
            if (statement != null) {
                try {
                    connection.close(); //关闭连接
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

    private Book getBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入书号：");
        int id = sc.nextInt();
        System.out.println("请输入书名：");
        sc.nextLine(); //有一个回车在输入缓冲区里面，需要提前用掉
        String name = sc.nextLine();
        System.out.println("请输入作者：");
        String author = sc.nextLine();
        System.out.println("请输入价格：");
        double price = sc.nextDouble();
        Book book = new Book(id, name, author, price, 0);
        return book;
    }
}
