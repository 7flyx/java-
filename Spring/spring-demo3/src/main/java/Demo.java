import com.baidu.beans.*;
import com.baidu.model.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-26
 * Time: 16:17
 * Description: 测试类
 */
public class Demo {

    public static void main(String[] args) {
        // 1 获取ApplicationContext 上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        // 2 获取Bean对象--id需要填类名是小驼峰的形式
        UserController userController = context.getBean("userController", UserController.class);
        // 3 调用方法
        userController.sayHi();

        // 调用Service注解的类
        UserService userService = context.getBean("userService", UserService.class);
        userService.sayHi();

        // 调用Repository注解的类
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        userRepository.sayHi();

        // 调用Component注解的类
        UserComponent userComponent = context.getBean("userComponent", UserComponent.class);
        userComponent.sayHi();

        // 调用Configuration注解的类
        UserConfiguration userConfiguration = context.getBean("userConfiguration", UserConfiguration.class);
        userConfiguration.sayHi();

        // 调用UService--当类名的第一个字母大写时，此处的ID需要使用大驼峰形式
        UService uService = context.getBean("UService", UService.class);
        uService.sayHi();

        // 调用方法注解的类--ID是
        // 没有起别名时，默认就是方法名；起别名后，就必须使用别名才能调用
        Users users = context.getBean("firstName", Users.class);
        System.out.println(users);

        // 也会将Bean注解所在的类，也装入容器中
        UsersBean usersBean = context.getBean("usersBean", UsersBean.class);
        System.out.println(usersBean);
    }
}
