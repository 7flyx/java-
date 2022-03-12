package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-10
 * Time: 17:01
 * Description: 常规的增删改查操作
 */
public class BlogDao {
    // 博客插入
    public void insert(Blog blog) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into Blog values(null,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setTimestamp(3, blog.getPostTime());
            statement.setInt(4, blog.getUserId());
            // 执行SQL
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("插入数据失败");
            } else {
                System.out.println("插入数据成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 回收相应的资源
            DBUtil.close(connection, statement, null);
        }
    }

    // 查询所有博客
    public List<Blog> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Blog> list = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                list.add(blog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return list; //将结果返回去
    }

    //查询其中某一篇博客--根据博客编号来查询
    public Blog selectOne(int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blogId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) { //根据博客编码（主键）查询，只可能查询到一条记录，因为主键值是唯一的
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setContent(resultSet.getString("content"));
                blog.setTitle(resultSet.getString("title"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                return blog;
            }
            return null; //没有查询到的情况
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null; //中途出现异常的情况
    }

    // 查询当前用户的全部文章
    public List<Blog> selectAllOfUser(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Blog> list = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            // 根据文章的发布时间，降序排序
            String sql = "select * from blog where userId = ? order by postTime desc";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) { //根据博客编码（主键）查询，只可能查询到一条记录，因为主键值是唯一的
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                String content = resultSet.getString("content");
                if (content.length() > 200) { // 限制博客列表的所展示的文章长度
                    content = content.substring(0, 200) + "...";
                }
                blog.setContent(content);
                blog.setTitle(resultSet.getString("title"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                list.add(blog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return list; //中途出现异常的情况
    }

    public void deleteOne(int blogId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blogId);
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("并未查询到该文章，导致删除失败");
            } else {
                System.out.println("成功查询到该文章，删除成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    // 测试类
    public static void main(String[] args) {
        Blog blog = new Blog();
//        blog.setBlogId(1);
//        blog.setContent("这是第一篇博客");
//        blog.setTitle("第一篇博客");
//        blog.setPostTime(new Timestamp(System.currentTimeMillis())); // 参数是时间戳
//        blog.setUserId(1);
        BlogDao blogDao = new BlogDao();
        blogDao.deleteOne(1);
    }
}
