package bean;

import common.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-06-02
 * Time: 9:33
 * Description: 写 一对一 数据库的。
 */
public class OneToOne {
    @Test
    public void oneToOne() {
        try (SqlSession session = DBUtil.openSession()) {
            List<Person> list = session.selectList("person.getOne");
            for (Person o : list) {
                System.out.println(o);
            }
        }
    }


}
