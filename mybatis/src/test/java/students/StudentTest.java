package students;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-04-06
 * Time: 15:14
 * Description:
 */
public class StudentTest {
    @Test
    public void select() {
//        不带任何参数的查询
        try (SqlSession session = DBUtil.openSession()) { // 自己获取session和关闭session
            // 直接通过session进行操作
            List<Student> objects = session.selectList("student.list");
            for (Student object : objects) {
                System.out.println(object);
            }
        }
    }

    @Test
    public void select2() {
        // 查询一个参数
        try (SqlSession session = DBUtil.openSession()) {
            Student student = session.selectOne("student.get", "彭于晏");
            System.out.println(student);
        }
    }

    @Test
    public void select3() {
//        第一种：传入多个参数的查询方法
        try (SqlSession session = DBUtil.openSession()) {
            Map<String, String> map = new HashMap<>();
            map.put("id","2");
            map.put("name","彭于晏");
            List<Student> student = session.selectList("student.get2", map);
            for (Student student1 : student) {
                System.out.println(student1);
            }
        }
    }

    @Test
    public void select4() {
//       第二种：传入多个参数的查询方法
        try (SqlSession session = DBUtil.openSession()) {
            Student student = new Student();
            student.setId(2);
            student.setName("彭于晏");
            List<Student> students = session.selectList("student.get2", student);
            for (Student student1 : students) {
                System.out.println(student1);
            }
        }
    }

    @Test
    public void select5() {
        // 多表查询
        try (SqlSession session = DBUtil.openSession()) {
            List<Student> students = session.selectList("student.get3");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    @Test
    public void select6() {
        // 多表查询
        try (SqlSession session = DBUtil.openSession()) {
            List<LinkedHashMap> students = session.selectList("student.get4");
            for (LinkedHashMap student : students) {
                System.out.println(student);
            }
        }
    }
}
