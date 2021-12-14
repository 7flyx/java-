package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:57
 * Description:
 */
public class FindOption implements IOption {
    public void work() {
        Connection connection = ConnectMysql.getConnection();
        Scanner sc = new Scanner(System.in);
        int option = -1;
        while (option != 1 &&  option != 2) {
            System.out.println("1、全列查询      2、关键字查询");
            option = sc.nextInt();
        }
        if(option == 1) {
            String sql = "select * from book where name like ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                System.out.println("请输入需要查询的书名：");
                String name = sc.nextLine();
                statement.setString(1, "%" + name + "%"); //模糊匹配
                // statement.setString(1, "'%" + name + "%'"); //模糊匹配，不需要拼接单引号
                ResultSet resultSet = statement.executeQuery();
                int size = 0;
                System.out.printf("%-4s %20s %10s %-5s %8s\n", "id", "name", "author", "price", "isBorrow");
                while (resultSet.next()) { //next方法，会将光标移动到当前行，第一次调用就是第一行
                    //根据select语句返回的数据，接收第一列、第二列……的数据
                    size++;
                    int id = resultSet.getInt(1);
                    String bookName = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    int isBorrow = resultSet.getInt(5);
                    System.out.printf("%-4d %20s %10s %-5.2f %-3s\n", id, bookName, author, price,
                            (isBorrow == 1 ? "已借出" : "未借出"));
                }
                System.out.println("查询成功, 共有" + size + "条记录");
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
        } else {
            String sql = "select * from book";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                int size = 0;
                System.out.printf("%-4s %20s %10s %-5s %8s\n", "id", "name", "author", "price", "isBorrow");
                while (resultSet.next()) { //next方法，会将光标移动到当前行，第一次调用就是第一行
                    //根据select语句返回的数据，接收第一列、第二列……的数据
                    size++;
                    int id = resultSet.getInt(1);
                    String bookName = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    int isBorrow = resultSet.getInt(5);
                    System.out.printf("%-4d %20s %10s %-5.2f %-3s\n", id, bookName, author, price,
                            (isBorrow == 1 ? "已借出" : "未借出"));
                }
                System.out.println("查询成功, 共有" + size + "条记录");
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
    }
}
