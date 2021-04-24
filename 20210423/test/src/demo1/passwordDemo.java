package demo1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class passwordDemo {
    public static void main(String[] args) {
        //模拟用户密码登陆
        String name1 = "hhh";
        String password1 = "hhh";

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 3; i++) {

            System.out.printf("请输入用户名:");
            String name = sc.nextLine();
            System.out.printf("请输入用户密码:");
            String password = sc.nextLine();

            if (name1.equals(name) && password.equals(password1)) {
                System.out.println("登陆成功");
                break;
            } else {
                if (2 - i == 0) {
                    System.out.println("账户已被冻结");
                } else {
                    System.out.println("密码错误，你还有" + (2 - i) + "次机会");
                }
            }
        }


    }

}
