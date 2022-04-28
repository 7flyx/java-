import com.baidu.beans.controller.ArtController;
import com.baidu.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-28
 * Time: 18:34
 * Description:
 */
public class Demo2 {
    public static void main(String[] args) {
        // 获取ApplicationContext 上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // 获取ArtController对象
        ArtController artController = context.getBean("artController", ArtController.class);

        // 调用里面的方法
        artController.addArticle("hello", "world");

    }
}
