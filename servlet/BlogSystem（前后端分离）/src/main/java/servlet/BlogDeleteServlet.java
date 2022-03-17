package servlet;

import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-17
 * Time: 12:53
 * Description: 删除博客
 */
@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 首先还是判断是不是处于登录状态
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        User user = Util.checkLogin(req);
        if (user == null) {
            // 当前不是登录状态
            resp.sendRedirect("blog_login.html");
            return;
        }
        // 判断当前博客的id和登录账户的id是否相等
        String blogId = req.getParameter("blogId");
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if (user.getUserId() != blog.getUserId()) { //当前登录账户和博客所属账户不一致，不能删除
            String html = "<h3>抱歉，您没有权限</h3>";
            resp.getWriter().write(html);
            return;
        }
        // 有权限的情况下，可以实现删除
        blogDao.deleteOne(Integer.parseInt(blogId));
        // 重定向到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
