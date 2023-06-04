package bean;

import common.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-06-04
 * Time: 15:33
 * Description: 一对多
 */
public class OneToMany {
    @Test
    public void test() {
        // 一个人有多张银行卡
        try (SqlSession session = DBUtil.openSession()){
            List<Person> objects = session.selectList("person.getPerson2");
            for(Person person : objects) {
                System.out.println(person);
            }
        }
    }

    @Test
    public void test2() {
        // 查询Person对象的全部信息，包括 银行卡、身份证、工作
        try(SqlSession session = DBUtil.openSession()) {
            List<Person> objects = session.selectList("person.getPerson2");
            for (Person person : objects) {
                System.out.println(person);
            }
        }
    }
}
