package dao;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-05-14
 * Time: 19:38
 * Description: 对题目进行增删改查
 */
public class ProblemDAO {
    public void insert(Problem problem) {
        if (problem == null) {
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into oj_table values(null, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, problem.getTitle());
            statement.setString(2, problem.getLevel());
            statement.setString(3, problem.getDescription());
            statement.setString(4, problem.getTemplateCode());
            statement.setString(5, problem.getTestCase());
            int ret = statement.executeUpdate();
            if (ret != 0) { // 影响到了数据库几行
                System.out.println("题目添加成功");
            } else {
                System.out.println("题目添加失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    public void delete(int problemID) {
        if (problemID < 1) {
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from oj_table where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemID);
            int ret = statement.executeUpdate();
            if (ret != 0) {
                System.out.println("题目删除成功");
            } else {
                System.out.println("题目删除失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    // 题目列表页时，需要查询全部的题目--当然也还可以实现根据偏移量来查询数据，对应到前端就需要实现分页器功能
    public List<Problem> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Problem> list = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            // 当前只是题目列表页，只需要题目的一部分信息
            String sql = "select id, title, level from oj_table";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                list.add(problem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return list;
    }

    // 题目详情页
    public Problem selectOne(int problemID) {
        if (problemID < 1) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from oj_table where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, problemID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setTitle(resultSet.getString("title"));
                problem.setLevel(resultSet.getString("level"));
                problem.setDescription(resultSet.getString("description"));
                problem.setTemplateCode(resultSet.getString("templateCode"));
                problem.setTestCase(resultSet.getString("testCase"));
                return problem;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public static void main(String[] args) {
        Problem problem = new Problem();
        problem.setId(1);
        problem.setTestCase(
                "import java.util.Arrays;\n" +
                "import java.util.HashMap;\n" +
                "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        int[] arr = {2,7,11,15};\n" +
                "        int target = 9;\n" +
                "        int[] testAns = test(arr, target);\n" +
                "        int[] userAns = solution.twoSum(arr, target);\n" +
                "        if (testAns.length != userAns.length) {\n" +
                "            System.out.println(\"fail\");\n" +
                "        } else {\n" +
                "            boolean accurate = true;\n" +
                "            for (int i = 0; i < testAns.length; i++) {\n" +
                "                if (testAns[i] != userAns[i]) {\n" +
                "                    accurate = false;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "            if(!accurate) {\n" +
                "                System.out.println(\"TestData：\" + Arrays.toString(arr));\n" +
                "                System.out.println(\"Output：\" +Arrays.toString(userAns));\n" +
                "                System.out.println(\"ExpectOutput：\" + Arrays.toString(testAns));\n" +
                "            } else {\n" +
                "                System.out.println(\"success\");\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public static int[] test(int[] nums, int target) {\n" +
                "        if (nums == null || nums.length < 1) {\n" +
                "            return new int[0];\n" +
                "        }\n" +
                "\n" +
                "        HashMap<Integer, Integer> map = new HashMap<>();\n" +
                "        for (int i = 0; i < nums.length; i++) {\n" +
                "            int x = nums[i];\n" +
                "            if (map.containsKey(target - x)) {\n" +
                "                return new int[] {i, map.get(target - x)};\n" +
                "            }\n" +
                "            map.put(x, i);\n" +
                "        }\n" +
                "        return new int[0];\n" +
                "    }\n" +
                "}\n");
        problem.setDescription("两数之和");
        problem.setTitle("两数之和");
        problem.setLevel("简单");
        problem.setTemplateCode("class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "\n" +
                "    }\n" +
                "}");
        ProblemDAO dao = new ProblemDAO();
        dao.insert(problem);

    }
}
