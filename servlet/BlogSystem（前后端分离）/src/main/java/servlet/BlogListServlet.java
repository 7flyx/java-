package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-15
 * Time: 17:24
 * Description: 返回blog_list页面的json数据。
 * 根据req进行判断，查看是否存在queryString，若其中有blogID，则表示需要返回整篇文章（博客详情页）
 */
@WebServlet("/blog")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        //判断当前用户是否处于登录状态
        User user = Util.checkLogin(req);
        if (user == null) {
            // 理论上来说，重定向到登录页面可行
            // 但是当前方法是被ajax调用的，而ajax无法处理重定向操作
            // 所有我们只能通过前端的js代码进行重定向
            //  resp.sendRedirect("blog_login.html");
            // ajax那边，有一个error的属性，表示返回错误时，会调用那边的回调函数
            resp.setStatus(403); // 403表示没权限，这里就表示没登录的状况
            return;
        }
        // 返回相应的数据
        String blogId = req.getParameter("blogId"); //看看是否存在blogID
        ObjectMapper objectMapper = new ObjectMapper();
        if(blogId == null) {
            // 返回的是博客列表页
            BlogDao blogDao = new BlogDao();
            List<Blog> blogs = blogDao.selectAll();
            String jsonString = objectMapper.writeValueAsString(blogs);
            resp.getWriter().write(jsonString); // 将json格式的数据传输给前端
        } else {
            // 返回的具体某一篇博客（博客详情页）
            BlogDao blogDao = new BlogDao();
            Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
            String jsonString = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(jsonString);
        }
    }
}
