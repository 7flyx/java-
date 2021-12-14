package demo;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 20:47
 * Description: 测试类
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int isAdministrator = -1;
        int option = 0;
        Person user = null;
        while (isAdministrator != 1 && isAdministrator != 2) {
            System.out.println("---------图书管理系统--------");
            System.out.println("------ 1、管理员登录  -------");
            System.out.println("------ 2、学生用户登录 -------");
            System.out.println("---------------------------");
            System.out.print("请选择登录用户（比如1）：");
            isAdministrator = sc.nextInt();
        }
        //这里的登录用户，可以手动输入，这里就不改了
        user = isAdministrator == 1? new Administrator(1, "root") : new OrdinaryUser(23, "student");
        if (isAdministrator == 1) {
            do {
                menuOfAdministrator();
                System.out.println("请选择操作（比如0）：");
                option = sc.nextInt();
                if (option >= 1 && option <= 4) {
                    user.options[option - 1].work(); //调用相应的接口
                }
            } while (option != 0); //如果是0的话，就结束循环了
        } else {
            do {
                menuOfOrdinaryUser();
                System.out.println("请选择操作（比如0）：");
                option = sc.nextInt();
                if (option >= 1 && option <= 3) {
                    user.options[option - 1].work(); //调用相应的接口
                }
            } while (option != 0); //如果是0的话，就结束循环了
        }
        System.out.println("欢迎下次使用，谢谢！");
    }

    public static void menuOfAdministrator() {
        System.out.println("---------图书管理系统（管理端）--------");
        System.out.println("---- 0、退出系统    1、新增图书  -----");
        System.out.println("---- 2、删除图书    3、修改图书  -----");
        System.out.println("---- 4、查询图书               -----");
        System.out.println("----------------------------------");

    }

    public static void menuOfOrdinaryUser() {
        System.out.println("---------图书管理系统（学生端）--------");
        System.out.println("---- 0、退出系统    1、查询图书  -----");
        System.out.println("---- 2、借阅图书    3、归还图书  -----");
        System.out.println("----------------------------------");

    }
}
