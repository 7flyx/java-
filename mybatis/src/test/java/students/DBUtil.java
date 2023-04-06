package students;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-06
 * Time: 15:49
 * Description:
 */
public class DBUtil {
    private static SqlSessionFactory factory; // 构建SQL session的工厂

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
//        首先需要一个工厂构建器
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            创建一个工厂
            factory = builder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() { // 返回一个session 会话值，上层调用自己进行session资源的回收
        return factory.openSession();
    }
}
