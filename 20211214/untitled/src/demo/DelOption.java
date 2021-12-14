package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:56
 * Description: 删除操作
 */
public class DelOption implements IOption {
    public void work() {
        Connection connection = ConnectMysql.getConnection();
        String sql = "delete from book where id = ? and name = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入需要删除的书号：");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("请输入需要删除的书名：");
            String name = sc.nextLine();
            statement.setInt(1, id);
            statement.setString(2, name);
            int ret = statement.executeUpdate();
            System.out.println(ret != 0? "删除成功": "删除失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally { //关闭相应的资源
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close(); //关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
