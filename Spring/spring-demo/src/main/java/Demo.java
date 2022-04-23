import com.my.beans.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-23
 * Time: 19:57
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        // 获取Spring上下文对象，从这个对象就能够拿取到bean对象--参数是resources里的配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        // 拿取对象
        User user = (User) applicationContext.getBean("user"); //参数是id
        // 调用user里面的方法
        user.sayHi();
    }
}
