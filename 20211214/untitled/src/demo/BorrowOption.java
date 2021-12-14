package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 17:00
 * Description:
 */
public class BorrowOption implements IOption {
    public void work() {
        Connection connection = ConnectMysql.getConnection();
        String sql = "update book set isborrow = 1 where name = ?";
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;

        try {
            statement = connection.prepareStatement(sql);
            System.out.println("请输入你需要借阅图书的书名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();

            String sql2 = "select isborrow from book where name = ?";
            statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, name);
            ResultSet set = statement2.executeQuery();
            if (!set.next()) return; //没有这本书，直接返回。返回之前，会先执行finally的语句
            if (set.getInt(1) == 1) {
                System.out.println("该图书已经被借阅了，请借阅其他图书！！！");
                return; //也是直接返回
            }
            statement.setString(1, name);
            int ret = statement.executeUpdate();
            System.out.println(ret == 1 ? "借阅成功" : "借阅失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(statement2 != null) {
                try {
                    statement2.close();
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
}
