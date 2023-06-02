package students;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
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
            map.put("id", "2");
            map.put("name", "彭于晏");
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
            List<Map> students = session.selectList("student.get5");
            for (Map student : students) {
                System.out.println(student);
            }
        }
    }

    @Test
    public void insert() {
        try (SqlSession session = DBUtil.openSession(true)) {
            Student student = new Student();
            student.setId(5);
            student.setName("张飞");
            student.setManager(new Manager(2, "刘备"));
            session.insert("student.insert", student);
        }
    }

    @Test
    public void insertList() {
        try (SqlSession session = DBUtil.openSession(true)) {
            Student stu1 = new Student(6, "小张", new Manager(2, "小张"));
            Student stu2 = new Student(7, "小刘", new Manager(2, "小张"));
            List<Student> list = new ArrayList<>();
            list.add(stu1);
            list.add(stu2);
            session.insert("student.insertList", list);
        }
    }

    @Test
    public void update() {
        try (SqlSession session = DBUtil.openSession(true)) {
            Student stu = new Student();
            stu.setId(1);
            stu.setName("pengyuyan");
            session.update("student.update", stu);
        }
    }

    @Test
    public void delete() {
        try (SqlSession session = DBUtil.openSession(true)) {
            session.delete("student.delete", 1);
        }
    }

    @Test
    public void deleteList() {
        try (SqlSession session = DBUtil.openSession()) {
            List<Integer> list = new ArrayList<>();
            list.add(2);
            list.add(3);
            list.add(4);
            session.delete("student.deleteList", list);
            session.commit(); // 提交事务
        }
    }

    @Test
    public void dynamicSQL() {
        try (SqlSession session = DBUtil.openSession()) {
            /*
                select * from student where id < 7 or name like '%刘%'
             */
            Map<String, Object> map = new HashMap<>();
            map.put("id", 7);
            map.put("name", "%刘%");
            List<Student> objects = session.selectList("student.dynamicSQL1", map);
            for (Student stu : objects) {
                System.out.println(stu);
            }
        }
    }

    @Test
    public void pageSelect() {
        try (SqlSession session = DBUtil.openSession()) {
            // 由PageHelper插件提供的函数，用于分页查询
            PageHelper.startPage(2, 2);
            Student stu = new Student();
            List<Student> list = session.selectList("student.pageSelect", stu);
            for (Student st : list) {
                System.out.println(st);
            }
        }
    }

}
