import book.BookList;
import users.Administrator;
import users.NormalUser;
import users.User;

import java.util.Scanner;

/**
 * Created by flyx
 * Description: 测试图书管理系统
 * User: 听风
 * Date: 2021-09-17
 * Time: 14:30
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("=========图书管理系统==========");
        Scanner sc = new Scanner(System.in);
        System.out.println("登陆账户：");

        String name = sc.next();
        System.out.println("1->管理用户       0->普通用户");
        int identity = -1;
        while (true) {
            identity = sc.nextInt();
            if (identity == 1 || identity == 0) {
                break;
            }
            System.out.println("输入参数有误，请重新输入!!!");
            System.out.println("1->管理用户       0->普通用户");
        }

        BookList list = new BookList(10);
        User user = identity == 1? new Administrator(name) : new NormalUser(name);
        while (true) {
            int choice = user.menu();
            user.doOperation(choice, list);
        }

    }
}
