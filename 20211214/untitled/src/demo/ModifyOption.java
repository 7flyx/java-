package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:57
 * Description:
 */
public class ModifyOption implements IOption {
    public void work() {
        Connection connection = ConnectMysql.getConnection();
        String sql = "update book set id = ?, name = ?, author = ?, price = ?, isborrow = ? where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("请输入需要图书的书号：");
            Scanner sc = new Scanner(System.in);
            int oldId = sc.nextInt();
            Book book = getBook(); //获取图书新的信息
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getIsBorrow());
            statement.setInt(6, oldId);

            int ret = statement.executeUpdate();
            System.out.println(ret == 1 ? "修改成功" : "修改失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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

    private Book getBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入新的书号：");
        int id = sc.nextInt();
        System.out.println("请输入新的书名：");
        sc.nextLine(); //有一个回车在输入缓冲区里面，需要提前用掉
        String name = sc.nextLine();
        System.out.println("请输入新的作者：");
        String author = sc.nextLine();
        System.out.println("请输入新的价格：");
        double price = sc.nextDouble();
        int isBorrow = -1;
        while (isBorrow != 1 && isBorrow != 0) {
            System.out.println("图书是否被借出（已借出输入1，未借出输入0）：");
            isBorrow = sc.nextInt();
        }
        Book book = new Book(id, name, author, price, isBorrow);
        return book;
    }
}
