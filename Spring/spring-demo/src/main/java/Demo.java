import com.my.beans.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-23
 * Time: 19:57
 * Description: resources下的spring-config.xml中的Beans可以注入多个重复的类。
 *              在beans里面，注入一次类，就相当于创建一次对象，后期在getBean时，只会拿到这个对象的引用。
 *              要想每次getBean时，拿到不同的对象，就需要改动配置，后期再说
 */
public class Demo {
    public static void main(String[] args) {
        // 1、 通过ApplicationContext上下文对象，获取Bean对象
        // 获取Spring上下文对象，从这个对象就能够拿取到bean对象--参数是resources里的配置文件
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
//        // 拿取对象
//        User user = (User) applicationContext.getBean("user"); //参数是id
//        // 调用user里面的方法
//        user.sayHi();

        // 2、通过BeanFactory，这个也是可以获取Bean对象的，new时也可以是 ClassPathXMLApplicationContext
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        //BeanFactory beanFactory1 = new XmlBeanFactory(new ClassPathResource("spring-config.xml")); // 这个也行，只是这个方法现在被弃用了
        User user = (User) beanFactory.getBean("user"); // 通过注入Bean时的ID获取对象。强转时，如果返回的是null，则会报错
        //User user1 = beanFactory.getBean(User.class); // 通过Class对象进行获取，但是这个方法只能在Beans只注入过一次当前类时使用。
        User user2 = beanFactory.getBean("user", User.class); // 这个就可以根据ID和Class两个参数进行获取对象，常用

        // 判断两个getBean，拿到的是否是同一个对象
        System.out.println(user);
        System.out.println(user2);
    }
}
