package dao;

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

    }

    public void delete(int problemID) {

    }

    // 题目列表页时，需要查询全部的题目--当然也还可以实现根据偏移量来查询数据，对应到前端就需要实现分页器功能
    public List<Problem> selectAll() {
        return null;
    }

    // 题目详情页
    public Problem selectOne(int problemID) {
        return null;
    }
}
